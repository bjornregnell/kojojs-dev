package kojo

import java.util.Random

import scala.scalajs.js

import org.scalajs.dom.document
import org.scalajs.dom.html
import org.scalajs.dom.raw.KeyboardEvent
import org.scalajs.dom.window

import com.vividsolutions.jts.geom.Coordinate

import kojo.doodle.Color
import pixiscalajs.PIXI
import pixiscalajs.PIXI.RendererOptions

class TurtleWorld {
  PIXI.Pixi
  val fiddleContainer =
    document.getElementById("fiddle-container").asInstanceOf[html.Div]
  val canvas_holder =
    document.getElementById("canvas-holder").asInstanceOf[html.Div]
  val (width, height) =
    (fiddleContainer.clientWidth, fiddleContainer.clientHeight)
  var renderer = PIXI.Pixi.autoDetectRenderer(width, height, rendererOptions())
  val stage = new PIXI.Container()
  init()

  def init() {
    render()
    canvas_holder.appendChild(renderer.view)
    stage.name = "Stage"
    stage.width = width
    stage.height = height
    stage.interactive = true
    stage.setTransform(width / 2, height / 2, 1, -1, 0, 0, 0, 0, 0)
    initEvents()
  }

  def addTurtleLayer(layer: PIXI.Container): Unit = {
    stage.addChild(layer)
  }

  val MaxBurst = 100
  var burstCount = 0
  def scheduleLater(fn: () => Unit): Unit = {
    burstCount += 1
    if (burstCount < MaxBurst) {
      fn()
    }
    else {
      window.setTimeout(fn, 0)
      burstCount = 0
    }
  }

  def addSprite(sprite: PIXI.Sprite): Unit = {
    def endFrame(): Unit = {
      stage.addChild(sprite)
      render()
    }

    scheduleLater(endFrame)
  }

  def render(): Unit = {
    renderer.render(stage)
  }

  def rendererOptions(
    antialias:         Boolean = true,
    resolution:        Double  = 1,
    backgroundColor:   Int     = 0xFFFFFF,
    clearBeforeRender: Boolean = true
  ): RendererOptions = {
    js.Dynamic
      .literal(
        antialias = antialias,
        resolution = resolution,
        backgroundColor = backgroundColor,
        clearBeforeRender = clearBeforeRender
      )
      .asInstanceOf[RendererOptions]
  }

  def setBackground(color: Color): Unit = {
    renderer.backgroundColor = color.toRGBDouble
  }

  var animatiing = false
  def animate(fn: => Unit): Unit = {
    animatiing = true
    window.requestAnimationFrame { t =>
      fn
      if (animatiing) {
        animate(fn)
      }
    }
  }

  def stopAnimation(): Unit = {
    animatiing = false
  }

  val noPic = TurtlePicture { t =>
  }(this)
  @volatile var stageBorder: Picture = _
  @volatile var stageLeft: Picture = _
  @volatile var stageTop: Picture = _
  @volatile var stageRight: Picture = _
  @volatile var stageBot: Picture = _
  @volatile var stageArea: Picture = _

  def clearStage() {
    stageBorder = noPic
    stageLeft = noPic
    stageTop = noPic
    stageRight = noPic
    stageBot = noPic
  }

  def drawStage(fillc: Color)(implicit turtleWorld: TurtleWorld) {
    def border(size: Double) = TurtlePicture { t =>
      t.setPenThickness(0)
      t.forward(size)
    }

    val xmax = stage.position.x.abs
    val ymax = stage.position.y.abs

    stageLeft = border(height)
    stageLeft.translate(-xmax, -ymax)

    stageTop = border(width)
    stageTop.translate(-xmax, ymax)
    stageTop.rotate(-90)

    stageRight = border(height)
    stageRight.translate(xmax, -ymax)

    stageBot = border(width)
    stageBot.translate(-xmax, -ymax)
    stageBot.rotate(-90)

    stageArea = TurtlePicture { t =>
      t.setFillColor(fillc)
      t.setPenColor(Color.darkGray)
      for (_ <- 1 to 2) {
        t.forward(height)
        t.right()
        t.forward(width)
        t.right()
      }
    }
    stageArea.translate(-xmax, -ymax)

    stageBorder = GPics(
      stageLeft,
      stageTop,
      stageRight,
      stageBot
    )

    stageArea.draw()
    stageBorder.draw()
  }

  def bounceVecOffStage(v: Vector2D, p: Picture): Vector2D = {
    val stageparts = List(stageTop, stageBot, stageLeft, stageRight)
    p.collision(stageparts).map {
      _ match {
        case p if p == stageTop   => Vector2D(v.x, -v.y)
        case p if p == stageBot   => Vector2D(v.x, -v.y)
        case p if p == stageLeft  => Vector2D(-v.x, v.y)
        case p if p == stageRight => Vector2D(-v.x, v.y)
        case _                    => v
      }
    }.get
  }

  def collidesWithStage(p: Picture): Boolean = {
    val stageparts = List(stageTop, stageBot, stageLeft, stageRight)
    p.collision(stageparts).isDefined
  }

  def bouncePicVectorOffPic(pic: Picture, vel: Vector2D, obstacle: Picture, rg: Random): Vector2D = {
    // returns points on the obstacle that contain the given collision coordinate
    def obstacleCollPoints(c: Coordinate): Option[js.Array[Coordinate]] = {
      obstacle.picGeom.getCoordinates.sliding(2).find { cs =>
        val xcheck = if (cs(0).x > cs(1).x)
          cs(0).x >= c.x && c.x >= cs(1).x
        else
          cs(0).x <= c.x && c.x <= cs(1).x

        val ycheck = if (cs(0).y > cs(1).y)
          cs(0).y >= c.y && c.y >= cs(1).y
        else
          cs(0).y <= c.y && c.y <= cs(1).y
        xcheck && ycheck
      }
    }
    // returns vector for obstacle boundary segment that contains the collision point
    def obstacleCollVector(c: Coordinate) = makeVectorFromCollPoints(obstacleCollPoints(c))

    // creates a vector out of two (collision) points
    def makeVectorFromCollPoints(cps: Option[js.Array[Coordinate]]) = cps match {
      case Some(cs) =>
        Vector2D(cs(0).x - cs(1).x, cs(0).y - cs(1).y)
      case None =>
        println("Warning: unable to determine collision vector; generating random vector")
        Vector2D(rg.nextDouble, rg.nextDouble)
    }

    def collisionVector = {
      val pt = obstacle.intersection(pic)
      val iCoords = pt.getCoordinates
      //      println(s"***\nIntersection shape: ${pt}")
      //      println(s"Intersection shape coords: ${iCoords.toVector}")
      if (iCoords.length == 0) {
        Vector2D(rg.nextDouble, rg.nextDouble).normalize
      }
      else {
        if (iCoords.length == 1) {
          val cv1 = obstacleCollVector(iCoords(0))
          cv1.normalize
        }
        else {
          val c1 = iCoords(0)
          val c2 = iCoords(iCoords.length - 1)
          val obsPts1 = obstacleCollPoints(c1)
          val obsPts2 = obstacleCollPoints(c2)
          if (obsPts1.isDefined && obsPts2.isDefined) {
            //            println(s"Obstacle points #1: ${obsPts1.get.toVector}")
            //            println(s"Obstacle points #2: ${obsPts2.get.toVector}")
            val s1 = collection.mutable.HashSet.empty[Coordinate]
            s1 += obsPts1.get(0); s1 += obsPts1.get(1)
            val s2 = collection.mutable.HashSet.empty[Coordinate]
            s2 += obsPts2.get(0); s2 += obsPts2.get(1)
            val s1s2 = s1.intersect(s2)
            if (s1s2.isEmpty) {
              //              println("No common points in obstacle points #1 and #2")
              val cv1 = makeVectorFromCollPoints(obsPts1)
              val cv2 = makeVectorFromCollPoints(obsPts2)
              //              println(s"cv1: $cv1")
              //              println(s"cv2: $cv2")
              cv1.normalize + cv2.normalize
            }
            else {
              val obsCommonPt = s1s2.head
              //              println(s"Common point in obstacle points #1 and #2: ${obsCommonPt}")
              s1 -= obsCommonPt
              s2 -= obsCommonPt
              val cv1 = makeVectorFromCollPoints(Some(js.Array(c1, obsCommonPt)))
              val cv2 = makeVectorFromCollPoints(Some(js.Array(obsCommonPt, c2)))
              //              println(s"cv1: $cv1")
              //              println(s"cv2: $cv2")
              (cv1 + cv2).normalize
            }
          }
          else {
            Vector2D(rg.nextDouble, rg.nextDouble).normalize
          }
        }
      }
    }
    def pullbackCollision() = {
      val velNorm = vel.normalize
      val v2 = velNorm.rotate(180)
      val velMag = vel.magnitude
      var pulled = 0
      while (pic.collidesWith(obstacle) && pulled < velMag) {
        pic.offset(v2)
        pulled += 1
      }
      pic.offset(velNorm)
    }

    pullbackCollision()
    val cv = collisionVector
    //    println(s"cv: $cv\n***")
    vel.bounceOff(cv)
  }



  val pressedKeys = new collection.mutable.HashSet[Int]

  def initEvents(): Unit = {
    def keyDown(e: KeyboardEvent): Unit = {
      pressedKeys.add(e.keyCode)
    }
    def keyUp(e: KeyboardEvent): Unit = {
      pressedKeys.remove(e.keyCode)
    }
    window.addEventListener("keydown", keyDown, false)
    window.addEventListener("keyup", keyUp, false)
  }

  def isKeyPressed(keyCode: Int) = pressedKeys.contains(keyCode)
}
