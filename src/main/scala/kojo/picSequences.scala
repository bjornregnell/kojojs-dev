package kojo

import com.vividsolutions.jts.geom.Geometry
import kojo.PicCache.freshPics
import kojo.doodle.Color
import pixiscalajs.PIXI

import scala.concurrent.Future

abstract class BasePicSequence(pics: Seq[Picture]) extends Picture with ReadyPromise {
  import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
  val tnode = new PIXI.Container()

  lazy val childrenReady: Future[Unit] = {
    val futures = pics map (_.ready)
    futures.reduce { (f1, f2) => for (_ <- f1; _ <- f2) yield () }
  }

  // Note - pic sequences get ready only after draw is called on them
  def layoutChildren(): Unit
  def layout(): Unit = {
    layoutChildren()
    makeDone()
  }

  def realDraw(): Unit = {
    pics.foreach { p =>
      p.draw()
      tnode.addChild(p.tnode)
    }
    kojoWorld.addLayer(tnode)
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
    childrenReady.foreach { _ =>
      layout()
    }
  }

  def initGeom() = {
    var pg = pics(0).picGeom
    pics.tail.foreach { pic =>
      pg = pg union pic.picGeom
    }
    pg
  }

  def setFillColor(c: Color): Unit = {
    pics.foreach { p =>
      p.setFillColor(c)
    }
  }

  def setPenColor(c: Color): Unit = {
    pics.foreach { p =>
      p.setPenColor(c)
    }
  }

  def setPenThickness(t: Double): Unit = {
    pics.foreach { p =>
      p.setPenThickness(t)
    }
  }

  def erase(): Unit = {
    kojoWorld.removeLayer(tnode)
  }

  def picsCopy = pics.map { _.copy }
}

object GPics {
  def apply(pics: collection.immutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new GPics(freshPics(pics))
  def apply(pics: collection.mutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new GPics(freshPics(pics))
  def apply(pics: Picture*)(implicit kojoWorld: KojoWorld) = new GPics(freshPics(pics))
}

class GPics(pics: Seq[Picture])(implicit val kojoWorld: KojoWorld) extends BasePicSequence(pics) {
  def layoutChildren(): Unit = {}
  def copy = new GPics(picsCopy)
}

object GPicsCentered {
  def apply(pics: collection.immutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new GPicsCentered(freshPics(pics))
  def apply(pics: collection.mutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new GPicsCentered(freshPics(pics))
  def apply(pics: Picture*)(implicit kojoWorld: KojoWorld) = new GPicsCentered(freshPics(pics))
}

class GPicsCentered(pics: Seq[Picture])(implicit val kojoWorld: KojoWorld) extends BasePicSequence(pics) {
  def layoutChildren(): Unit = {
    var prevPic: Option[Picture] = None
    pics.foreach { pic =>
      prevPic match {
        case Some(ppic) =>
          val pbounds = ppic.bounds
          val bounds = pic.bounds
          val tx = pbounds.x - bounds.x + (pbounds.width - bounds.width) / 2
          val ty = pbounds.y - bounds.y + (pbounds.height - bounds.height) / 2
          pic.offset(tx, ty)
        case None =>
      }
      prevPic = Some(pic)
    }
  }

  def copy = new GPicsCentered(picsCopy)
}

object HPics {
  def apply(pics: collection.immutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new HPics(freshPics(pics))
  def apply(pics: collection.mutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new HPics(freshPics(pics))
  def apply(pics: Picture*)(implicit kojoWorld: KojoWorld) = new HPics(freshPics(pics))
}

class HPics(pics: Seq[Picture])(implicit val kojoWorld: KojoWorld) extends BasePicSequence(pics) {
  def layoutChildren(): Unit = {
    var ox = 0.0
    pics.foreach { pic =>
      pic.offset(ox, 0)
      val nbounds = pic.bounds
      ox = nbounds.x + nbounds.width
    }
  }

  def copy = new HPics(picsCopy)
}

object HPicsCentered {
  def apply(pics: collection.immutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new HPicsCentered(freshPics(pics))
  def apply(pics: collection.mutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new HPicsCentered(freshPics(pics))
  def apply(pics: Picture*)(implicit kojoWorld: KojoWorld) = new HPicsCentered(freshPics(pics))
}

class HPicsCentered(pics: Seq[Picture])(implicit val kojoWorld: KojoWorld) extends BasePicSequence(pics) {
  def layoutChildren(): Unit = {
    var prevPic: Option[Picture] = None
    pics.foreach { pic =>
      prevPic match {
        case Some(ppic) =>
          val pbounds = ppic.bounds
          val tx = pbounds.x + pbounds.width
          pic.offset(tx, 0)
          val bounds = pic.bounds
          val ty = pbounds.y - bounds.y + (pbounds.height - bounds.height) / 2
          val tx2 = pbounds.x + pbounds.width - bounds.x
          pic.offset(tx2, ty)
        case None =>
      }
      prevPic = Some(pic)
    }
  }

  def copy = new HPicsCentered(picsCopy)
}

object VPics {
  def apply(pics: collection.immutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new VPics(freshPics(pics))
  def apply(pics: collection.mutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new VPics(freshPics(pics))
  def apply(pics: Picture*)(implicit kojoWorld: KojoWorld) = new VPics(freshPics(pics))
}

class VPics(pics: Seq[Picture])(implicit val kojoWorld: KojoWorld) extends BasePicSequence(pics) {
  def layoutChildren(): Unit = {
    var oy = 0.0
    pics.foreach { pic =>
      pic.offset(0, oy)
      val nbounds = pic.bounds
      oy = nbounds.y + nbounds.height
    }
  }

  def copy = new VPics(picsCopy)
}

object VPicsCentered {
  def apply(pics: collection.immutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new VPicsCentered(freshPics(pics))
  def apply(pics: collection.mutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new VPicsCentered(freshPics(pics))
  def apply(pics: Picture*)(implicit kojoWorld: KojoWorld) = new VPicsCentered(freshPics(pics))
}

class VPicsCentered(pics: Seq[Picture])(implicit val kojoWorld: KojoWorld) extends BasePicSequence(pics) {
  def layoutChildren(): Unit = {
    var prevPic: Option[Picture] = None
    pics.foreach { pic =>
      prevPic match {
        case Some(ppic) =>
          val pbounds = ppic.bounds
          val ty = pbounds.y + pbounds.height
          pic.offset(0, ty)
          val bounds = pic.bounds
          val tx = pbounds.x - bounds.x + (pbounds.width - bounds.width) / 2
          val ty2 = pbounds.y + pbounds.height - bounds.y
          pic.offset(tx, ty2)
        case None =>
      }
      prevPic = Some(pic)
    }
  }

  def copy = new VPicsCentered(picsCopy)
}

object BatchPics {
  def apply(pics: collection.immutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new BatchPics(pics)
  def apply(pics: collection.mutable.Seq[Picture])(implicit kojoWorld: KojoWorld) = new BatchPics(pics)
  def apply(pics: Picture*)(implicit kojoWorld: KojoWorld) = new BatchPics(pics)
}

class BatchPics(pics: Seq[Picture])(implicit val kojoWorld: KojoWorld) extends BasePicSequence(pics) {
  def layoutChildren(): Unit = {
    pics.tail.foreach { p =>
      p.invisible()
    }
  }

  var currPic = 0
  var lastDraw = System.currentTimeMillis
  def showNext(gap: Long) = {
    val currTime = System.currentTimeMillis
    if (currTime - lastDraw > gap) {
      pics(currPic).invisible()
      currPic += 1
      if (currPic == pics.size) {
        currPic = 0
      }
      pics(currPic).visible()
      lastDraw = currTime
    }
  }
  def showNext(): Unit = showNext(100)

  override def picGeom: Geometry = {
    if (!made) {
      return null
    }
    pgTransform.transform(pics(currPic).picGeom)
  }

  def copy = new BatchPics(picsCopy)
}

class PicScreen {
  import scala.collection.mutable.ArrayBuffer

  val pics = ArrayBuffer.empty[Picture]
  var drawn = false

  def add(ps: Picture*) {
    ps.foreach { pics.append(_) }
  }

  def add(ps: collection.immutable.Seq[Picture]) {
    ps.foreach { pics.append(_) }
  }

  def add(ps: collection.mutable.Seq[Picture]) {
    ps.foreach { pics.append(_) }
  }

  private def draw() {
    pics.foreach { _.draw() }
  }

  def hide() {
    pics.foreach { _.invisible() }
  }

  private def unhide() {
    pics.foreach { _.visible() }
  }

  def show() {
    if (!drawn) {
      draw()
      drawn = true
    }
    else {
      unhide()
    }
  }

  def erase() {
    pics.foreach { _.erase() }
  }
}
