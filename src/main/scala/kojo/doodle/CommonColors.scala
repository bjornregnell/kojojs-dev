// Borrowed from: https://github.com/underscoreio/doodle
package kojo.doodle

import kojo.syntax.uByte._

trait CommonColors {
  val aliceBlue = Color.rgb(0xf0.uByte, 0xf8.uByte, 0xff.uByte)
  val antiqueWhite = Color.rgb(0xfa.uByte, 0xeb.uByte, 0xd7.uByte)
  val aqua = Color.rgb(0x00.uByte, 0xff.uByte, 0xff.uByte)
  val aquamarine = Color.rgb(0x7f.uByte, 0xff.uByte, 0xd4.uByte)
  val azure = Color.rgb(0xf0.uByte, 0xff.uByte, 0xff.uByte)
  val beige = Color.rgb(0xf5.uByte, 0xf5.uByte, 0xdc.uByte)
  val bisque = Color.rgb(0xff.uByte, 0xe4.uByte, 0xc4.uByte)
  val black = Color.rgb(0x00.uByte, 0x00.uByte, 0x00.uByte)
  val blanchedAlmond = Color.rgb(0xff.uByte, 0xeb.uByte, 0xcd.uByte)
  val blue = Color.rgb(0x00.uByte, 0x00.uByte, 0xff.uByte)
  val blueViolet = Color.rgb(0x8a.uByte, 0x2b.uByte, 0xe2.uByte)
  val brown = Color.rgb(0xa5.uByte, 0x2a.uByte, 0x2a.uByte)
  val burlyWood = Color.rgb(0xde.uByte, 0xb8.uByte, 0x87.uByte)
  val cadetBlue = Color.rgb(0x5f.uByte, 0x9e.uByte, 0xa0.uByte)
  val chartReuse = Color.rgb(0x7f.uByte, 0xff.uByte, 0x00.uByte)
  val chocolate = Color.rgb(0xd2.uByte, 0x69.uByte, 0x1e.uByte)
  val coral = Color.rgb(0xff.uByte, 0x7f.uByte, 0x50.uByte)
  val cornflowerBlue = Color.rgb(0x64.uByte, 0x95.uByte, 0xed.uByte)
  val cornSilk = Color.rgb(0xff.uByte, 0xf8.uByte, 0xdc.uByte)
  val crimson = Color.rgb(0xdc.uByte, 0x14.uByte, 0x3c.uByte)
  val cyan = Color.rgb(0x00.uByte, 0xff.uByte, 0xff.uByte)
  val darkBlue = Color.rgb(0x00.uByte, 0x00.uByte, 0x8b.uByte)
  val darkCyan = Color.rgb(0x00.uByte, 0x8b.uByte, 0x8b.uByte)
  val darkGoldenrod = Color.rgb(0xb8.uByte, 0x86.uByte, 0x0b.uByte)
  val darkGray = Color.rgb(0xa9.uByte, 0xa9.uByte, 0xa9.uByte)
  val darkGrey = Color.rgb(0xa9.uByte, 0xa9.uByte, 0xa9.uByte)
  val darkGreen = Color.rgb(0x00.uByte, 0x64.uByte, 0x00.uByte)
  val darkKhaki = Color.rgb(0xbd.uByte, 0xb7.uByte, 0x6b.uByte)
  val darkMagenta = Color.rgb(0x8b.uByte, 0x00.uByte, 0x8b.uByte)
  val darkOliveGreen = Color.rgb(0x55.uByte, 0x6b.uByte, 0x2f.uByte)
  val darkOrange = Color.rgb(0xff.uByte, 0x8c.uByte, 0x00.uByte)
  val darkOrchid = Color.rgb(0x99.uByte, 0x32.uByte, 0xcc.uByte)
  val darkRed = Color.rgb(0x8b.uByte, 0x00.uByte, 0x00.uByte)
  val darkSalmon = Color.rgb(0xe9.uByte, 0x96.uByte, 0x7a.uByte)
  val darkSeaGreen = Color.rgb(0x8f.uByte, 0xbc.uByte, 0x8f.uByte)
  val darkSlateBlue = Color.rgb(0x48.uByte, 0x3d.uByte, 0x8b.uByte)
  val darkSlateGray = Color.rgb(0x2f.uByte, 0x4f.uByte, 0x4f.uByte)
  val darkSlateGrey = Color.rgb(0x2f.uByte, 0x4f.uByte, 0x4f.uByte)
  val darkTurquoise = Color.rgb(0x00.uByte, 0xce.uByte, 0xd1.uByte)
  val darkViolet = Color.rgb(0x94.uByte, 0x00.uByte, 0xd3.uByte)
  val deepPink = Color.rgb(0xff.uByte, 0x14.uByte, 0x93.uByte)
  val deepSkyBlue = Color.rgb(0x00.uByte, 0xbf.uByte, 0xff.uByte)
  val dimGray = Color.rgb(0x69.uByte, 0x69.uByte, 0x69.uByte)
  val dimGrey = Color.rgb(0x69.uByte, 0x69.uByte, 0x69.uByte)
  val dodgerBlue = Color.rgb(0x1e.uByte, 0x90.uByte, 0xff.uByte)
  val fireBrick = Color.rgb(0xb2.uByte, 0x22.uByte, 0x22.uByte)
  val floralWhite = Color.rgb(0xff.uByte, 0xfa.uByte, 0xf0.uByte)
  val forestGreen = Color.rgb(0x22.uByte, 0x8b.uByte, 0x22.uByte)
  val fuchsia = Color.rgb(0xff.uByte, 0x00.uByte, 0xff.uByte)
  val gainsboro = Color.rgb(0xdc.uByte, 0xdc.uByte, 0xdc.uByte)
  val ghostWhite = Color.rgb(0xf8.uByte, 0xf8.uByte, 0xff.uByte)
  val gold = Color.rgb(0xff.uByte, 0xd7.uByte, 0x00.uByte)
  val goldenrod = Color.rgb(0xda.uByte, 0xa5.uByte, 0x20.uByte)
  val gray = Color.rgb(0x80.uByte, 0x80.uByte, 0x80.uByte)
  val grey = Color.rgb(0x80.uByte, 0x80.uByte, 0x80.uByte)
  val green = Color.rgb(0x00.uByte, 0x80.uByte, 0x00.uByte)
  val greenYellow = Color.rgb(0xad.uByte, 0xff.uByte, 0x2f.uByte)
  val honeydew = Color.rgb(0xf0.uByte, 0xff.uByte, 0xf0.uByte)
  val hotpink = Color.rgb(0xff.uByte, 0x69.uByte, 0xb4.uByte)
  val indianRed = Color.rgb(0xcd.uByte, 0x5c.uByte, 0x5c.uByte)
  val indigo = Color.rgb(0x4b.uByte, 0x00.uByte, 0x82.uByte)
  val ivory = Color.rgb(0xff.uByte, 0xff.uByte, 0xf0.uByte)
  val khaki = Color.rgb(0xf0.uByte, 0xe6.uByte, 0x8c.uByte)
  val lavender = Color.rgb(0xe6.uByte, 0xe6.uByte, 0xfa.uByte)
  val lavenderBlush = Color.rgb(0xff.uByte, 0xf0.uByte, 0xf5.uByte)
  val lawngreen = Color.rgb(0x7c.uByte, 0xfc.uByte, 0x00.uByte)
  val lemonChiffon = Color.rgb(0xff.uByte, 0xfa.uByte, 0xcd.uByte)
  val lightBlue = Color.rgb(0xad.uByte, 0xd8.uByte, 0xe6.uByte)
  val lightCoral = Color.rgb(0xf0.uByte, 0x80.uByte, 0x80.uByte)
  val lightCyan = Color.rgb(0xe0.uByte, 0xff.uByte, 0xff.uByte)
  val lightGoldenrodYellow = Color.rgb(0xfa.uByte, 0xfa.uByte, 0xd2.uByte)
  val lightGray = Color.rgb(0xd3.uByte, 0xd3.uByte, 0xd3.uByte)
  val lightGrey = Color.rgb(0xd3.uByte, 0xd3.uByte, 0xd3.uByte)
  val lightGreen = Color.rgb(0x90.uByte, 0xee.uByte, 0x90.uByte)
  val lightPink = Color.rgb(0xff.uByte, 0xb6.uByte, 0xc1.uByte)
  val lightSalmon = Color.rgb(0xff.uByte, 0xa0.uByte, 0x7a.uByte)
  val lightSeaGreen = Color.rgb(0x20.uByte, 0xb2.uByte, 0xaa.uByte)
  val lightSkyBlue = Color.rgb(0x87.uByte, 0xce.uByte, 0xfa.uByte)
  val lightSlateGray = Color.rgb(0x77.uByte, 0x88.uByte, 0x99.uByte)
  val lightSlateGrey = Color.rgb(0x77.uByte, 0x88.uByte, 0x99.uByte)
  val lightSteelBlue = Color.rgb(0xb0.uByte, 0xc4.uByte, 0xde.uByte)
  val lightYellow = Color.rgb(0xff.uByte, 0xff.uByte, 0xe0.uByte)
  val lime = Color.rgb(0x00.uByte, 0xff.uByte, 0x00.uByte)
  val limeGreen = Color.rgb(0x32.uByte, 0xcd.uByte, 0x32.uByte)
  val linen = Color.rgb(0xfa.uByte, 0xf0.uByte, 0xe6.uByte)
  val magenta = Color.rgb(0xff.uByte, 0x00.uByte, 0xff.uByte)
  val maroon = Color.rgb(0x80.uByte, 0x00.uByte, 0x00.uByte)
  val mediumAquamarine = Color.rgb(0x66.uByte, 0xcd.uByte, 0xaa.uByte)
  val mediumBlue = Color.rgb(0x00.uByte, 0x00.uByte, 0xcd.uByte)
  val mediumOrchid = Color.rgb(0xba.uByte, 0x55.uByte, 0xd3.uByte)
  val mediumPurple = Color.rgb(0x93.uByte, 0x70.uByte, 0xd8.uByte)
  val mediumSeaGreen = Color.rgb(0x3c.uByte, 0xb3.uByte, 0x71.uByte)
  val mediumSlateBlue = Color.rgb(0x7b.uByte, 0x68.uByte, 0xee.uByte)
  val mediumSpringGreen = Color.rgb(0x00.uByte, 0xfa.uByte, 0x9a.uByte)
  val mediumTurquoise = Color.rgb(0x48.uByte, 0xd1.uByte, 0xcc.uByte)
  val mediumVioletRed = Color.rgb(0xc7.uByte, 0x15.uByte, 0x85.uByte)
  val midnightBlue = Color.rgb(0x19.uByte, 0x19.uByte, 0x70.uByte)
  val mintCream = Color.rgb(0xf5.uByte, 0xff.uByte, 0xfa.uByte)
  val mistyRose = Color.rgb(0xff.uByte, 0xe4.uByte, 0xe1.uByte)
  val moccasin = Color.rgb(0xff.uByte, 0xe4.uByte, 0xb5.uByte)
  val navajoWhite = Color.rgb(0xff.uByte, 0xde.uByte, 0xad.uByte)
  val navy = Color.rgb(0x00.uByte, 0x00.uByte, 0x80.uByte)
  val oldLace = Color.rgb(0xfd.uByte, 0xf5.uByte, 0xe6.uByte)
  val olive = Color.rgb(0x80.uByte, 0x80.uByte, 0x00.uByte)
  val oliveDrab = Color.rgb(0x6b.uByte, 0x8e.uByte, 0x23.uByte)
  val orange = Color.rgb(0xff.uByte, 0xa5.uByte, 0x00.uByte)
  val orangeRed = Color.rgb(0xff.uByte, 0x45.uByte, 0x00.uByte)
  val orchid = Color.rgb(0xda.uByte, 0x70.uByte, 0xd6.uByte)
  val paleGoldenrod = Color.rgb(0xee.uByte, 0xe8.uByte, 0xaa.uByte)
  val paleGreen = Color.rgb(0x98.uByte, 0xfb.uByte, 0x98.uByte)
  val paleTurquoise = Color.rgb(0xaf.uByte, 0xee.uByte, 0xee.uByte)
  val paleVioletRed = Color.rgb(0xd8.uByte, 0x70.uByte, 0x93.uByte)
  val papayaWhip = Color.rgb(0xff.uByte, 0xef.uByte, 0xd5.uByte)
  val peachPuff = Color.rgb(0xff.uByte, 0xda.uByte, 0xb9.uByte)
  val peru = Color.rgb(0xcd.uByte, 0x85.uByte, 0x3f.uByte)
  val pink = Color.rgb(0xff.uByte, 0xc0.uByte, 0xcb.uByte)
  val plum = Color.rgb(0xdd.uByte, 0xa0.uByte, 0xdd.uByte)
  val powderBlue = Color.rgb(0xb0.uByte, 0xe0.uByte, 0xe6.uByte)
  val purple = Color.rgb(0x80.uByte, 0x00.uByte, 0x80.uByte)
  val red = Color.rgb(0xff.uByte, 0x00.uByte, 0x00.uByte)
  val rosyBrown = Color.rgb(0xbc.uByte, 0x8f.uByte, 0x8f.uByte)
  val royalBlue = Color.rgb(0x41.uByte, 0x69.uByte, 0xe1.uByte)
  val saddleBrown = Color.rgb(0x8b.uByte, 0x45.uByte, 0x13.uByte)
  val salmon = Color.rgb(0xfa.uByte, 0x80.uByte, 0x72.uByte)
  val sandyBrown = Color.rgb(0xf4.uByte, 0xa4.uByte, 0x60.uByte)
  val seaGreen = Color.rgb(0x2e.uByte, 0x8b.uByte, 0x57.uByte)
  val seaShell = Color.rgb(0xff.uByte, 0xf5.uByte, 0xee.uByte)
  val sienna = Color.rgb(0xa0.uByte, 0x52.uByte, 0x2d.uByte)
  val silver = Color.rgb(0xc0.uByte, 0xc0.uByte, 0xc0.uByte)
  val skyBlue = Color.rgb(0x87.uByte, 0xce.uByte, 0xeb.uByte)
  val slateBlue = Color.rgb(0x6a.uByte, 0x5a.uByte, 0xcd.uByte)
  val slateGray = Color.rgb(0x70.uByte, 0x80.uByte, 0x90.uByte)
  val slateGrey = Color.rgb(0x70.uByte, 0x80.uByte, 0x90.uByte)
  val snow = Color.rgb(0xff.uByte, 0xfa.uByte, 0xfa.uByte)
  val springGreen = Color.rgb(0x00.uByte, 0xff.uByte, 0x7f.uByte)
  val steelBlue = Color.rgb(0x46.uByte, 0x82.uByte, 0xb4.uByte)
  val tan = Color.rgb(0xd2.uByte, 0xb4.uByte, 0x8c.uByte)
  val teal = Color.rgb(0x00.uByte, 0x80.uByte, 0x80.uByte)
  val thistle = Color.rgb(0xd8.uByte, 0xbf.uByte, 0xd8.uByte)
  val tomato = Color.rgb(0xff.uByte, 0x63.uByte, 0x47.uByte)
  val turquoise = Color.rgb(0x40.uByte, 0xe0.uByte, 0xd0.uByte)
  val violet = Color.rgb(0xee.uByte, 0x82.uByte, 0xee.uByte)
  val wheat = Color.rgb(0xf5.uByte, 0xde.uByte, 0xb3.uByte)
  val white = Color.rgb(0xff.uByte, 0xff.uByte, 0xff.uByte)
  val whiteSmoke = Color.rgb(0xf5.uByte, 0xf5.uByte, 0xf5.uByte)
  val yellow = Color.rgb(0xff.uByte, 0xff.uByte, 0x00.uByte)
  val yellowGreen = Color.rgb(0x9a.uByte, 0xcd.uByte, 0x33.uByte)
}
