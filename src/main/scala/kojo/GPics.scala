package kojo

import scala.concurrent.Future

import kojo.doodle.Color
import pixiscalajs.PIXI

object GPics {
  def apply(pics: List[Picture])(implicit kojoWorld: KojoWorld) = new GPics(pics)
  def apply(pics: Picture*)(implicit kojoWorld: KojoWorld) = new GPics(pics)
}

class GPics(pics: Seq[Picture])(implicit val kojoWorld: KojoWorld) extends Picture {
  val tnode = new PIXI.Container()

  pics.foreach { p =>
    tnode.addChild(p.tnode)
  }

  def made: Boolean = {
    val notMade = pics.find { p =>
      !p.made
    }
    notMade match {
      case Some(_) => false
      case None    => true
    }
  }

  import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
  lazy val ready: Future[Unit] = {
    val futures = pics map (_.ready)
    futures.reduce { (f1, f2) => for(_ <- f1; _ <- f2) yield ()}
  }

  def realDraw(): Unit = {
    ready.foreach { _ =>
      kojoWorld.addLayer(tnode)
    }
    
    // check why this is needed?
    pics.foreach { p =>
      p.updateGeomTransform()
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
    //    pics.foreach { p =>
    //      p.erase()
    //    }
  }
}
