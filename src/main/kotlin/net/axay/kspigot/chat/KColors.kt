package net.axay.kspigot.chat

import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import java.lang.reflect.Field
import java.util.*
import java.util.stream.Collectors

@Suppress("unused")
object KColors {
    val MAGIC: TextDecoration = TextDecoration.OBFUSCATED
    val BOLD: TextDecoration = TextDecoration.BOLD
    val STRIKETHROUGH: TextDecoration = TextDecoration.STRIKETHROUGH
    val UNDERLINE: TextDecoration = TextDecoration.UNDERLINED
    val ITALIC: TextDecoration = TextDecoration.ITALIC

    // DEFAULT COLORS
    /**
     * #0000AA
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#0000AA"></div>
     */
    val DARKBLUE: TextColor = TextColor.color(0, 0, 170)

    /**
     * #00AA00
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#00AA00"></div>
     */
    val DARKGREEN: TextColor = TextColor.color(0, 170, 0)

    /**
     * #00AAAA
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#00AAAA"></div>
     */
    val DARKAQUA: TextColor = TextColor.color(0, 170, 170)

    /**
     * #AA0000
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#AA0000"></div>
     */
    val DARKRED: TextColor = TextColor.color(170, 0, 0)

    /**
     * #AA00AA
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#AA00AA"></div>
     */
    val DARKPURPLE: TextColor = TextColor.color(170, 0, 170)

    /**
     * #FFAA00
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#FFAA00"></div>
     */
    val GOLD: TextColor = TextColor.color(255, 170, 0)

    /**
     * #AAAAAA
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#AAAAAA"></div>
     */
    val GRAY: TextColor = TextColor.color(170, 170, 170)

    /**
     * #555555
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#555555"></div>
     */
    val DARKGRAY: TextColor = TextColor.color(85, 85, 85)

    /**
     * #5555FF
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#5555FF"></div>
     */
    val BLUE: TextColor = TextColor.color(85, 85, 255)

    /**
     * #55FF55
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#55FF55"></div>
     */
    val GREEN: TextColor = TextColor.color(85, 255, 85)

    /**
     * #55FFFF
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#55FFFF"></div>
     */
    val AQUA: TextColor = TextColor.color(85, 255, 255)

    /**
     * #FF5555
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#FF5555"></div>
     */
    val RED: TextColor = TextColor.color(255, 85, 85)

    /**
     * #FF55FF
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#FF55FF"></div>
     */
    val LIGHTPURPLE: TextColor = TextColor.color(255, 85, 255)

    /**
     * #FFFF55
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#FFFF55"></div>
     */
    val YELLOW: TextColor = TextColor.color(255, 255, 85)

    /**
     * #FFFFFF
     * <div style="border:1px solid black;width:120px;height:120px;background-color:#FFFFFF"></div>
     */
    val WHITE: TextColor = TextColor.color(255, 255, 255)

    // ADDITIONAL COLORS
    /**
     * #F0F8FF
     * <div style="width:120px;height:120px;background-color:#F0F8FF"></div>
     */
    val ALICEBLUE: TextColor = TextColor.color(240, 248, 255)

    /**
     * #FAEBD7
     * <div style="width:120px;height:120px;background-color:#FAEBD7"></div>
     */
    val ANTIQUEWHITE: TextColor = TextColor.color(250, 235, 215)

    /**
     * #7FFFD4
     * <div style="width:120px;height:120px;background-color:#7FFFD4"></div>
     */
    val AQUAMARINE: TextColor = TextColor.color(127, 255, 212)

    /**
     * #F0FFFF
     * <div style="width:120px;height:120px;background-color:#F0FFFF"></div>
     */
    val AZURE: TextColor = TextColor.color(240, 255, 255)

    /**
     * #F5F5DC
     * <div style="width:120px;height:120px;background-color:#F5F5DC"></div>
     */
    val BEIGE: TextColor = TextColor.color(245, 245, 220)

    /**
     * #FFE4C4
     * <div style="width:120px;height:120px;background-color:#FFE4C4"></div>
     */
    val BISQUE: TextColor = TextColor.color(255, 228, 196)

    /**
     * #000000
     * <div style="width:120px;height:120px;background-color:#000000"></div>
     */
    val BLACK: TextColor = TextColor.color(0, 0, 0)

    /**
     * #FFEBCD
     * <div style="width:120px;height:120px;background-color:#FFEBCD"></div>
     */
    val BLANCHEDALMOND: TextColor = TextColor.color(255, 235, 205)

    /**
     * #8A2BE2
     * <div style="width:120px;height:120px;background-color:#8A2BE2"></div>
     */
    val BLUEVIOLET: TextColor = TextColor.color(138, 43, 226)

    /**
     * #A52A2A
     * <div style="width:120px;height:120px;background-color:#A52A2A"></div>
     */
    val BROWN: TextColor = TextColor.color(165, 42, 42)

    /**
     * #DEB887
     * <div style="width:120px;height:120px;background-color:#DEB887"></div>
     */
    val BURLYWOOD: TextColor = TextColor.color(222, 184, 135)

    /**
     * #5F9EA0
     * <div style="width:120px;height:120px;background-color:#5F9EA0"></div>
     */
    val CADETBLUE: TextColor = TextColor.color(95, 158, 160)

    /**
     * #7FFF00
     * <div style="width:120px;height:120px;background-color:#7FFF00"></div>
     */
    val CHARTREUSE: TextColor = TextColor.color(127, 255, 0)

    /**
     * #D2691E
     * <div style="width:120px;height:120px;background-color:#D2691E"></div>
     */
    val CHOCOLATE: TextColor = TextColor.color(210, 105, 30)

    /**
     * #FF7F50
     * <div style="width:120px;height:120px;background-color:#FF7F50"></div>
     */
    val CORAL: TextColor = TextColor.color(255, 127, 80)

    /**
     * #6495ED
     * <div style="width:120px;height:120px;background-color:#6495ED"></div>
     */
    val CORNFLOWERBLUE: TextColor = TextColor.color(100, 149, 237)

    /**
     * #FFF8DC
     * <div style="width:120px;height:120px;background-color:#FFF8DC"></div>
     */
    val CORNSILK: TextColor = TextColor.color(255, 248, 220)

    /**
     * #DC143C
     * <div style="width:120px;height:120px;background-color:#DC143C"></div>
     */
    val CRIMSON: TextColor = TextColor.color(220, 20, 60)

    /**
     * #00FFFF
     * <div style="width:120px;height:120px;background-color:#00FFFF"></div>
     */
    val CYAN: TextColor = TextColor.color(0, 255, 255)

    /**
     * #008B8B
     * <div style="width:120px;height:120px;background-color:#008B8B"></div>
     */
    val DARKCYAN: TextColor = TextColor.color(0, 139, 139)

    /**
     * #B8860B
     * <div style="width:120px;height:120px;background-color:#B8860B"></div>
     */
    val DARKGOLDENROD: TextColor = TextColor.color(184, 134, 11)

    /**
     * #BDB76B
     * <div style="width:120px;height:120px;background-color:#BDB76B"></div>
     */
    val DARKKHAKI: TextColor = TextColor.color(189, 183, 107)

    /**
     * #8B008B
     * <div style="width:120px;height:120px;background-color:#8B008B"></div>
     */
    val DARKMAGENTA: TextColor = TextColor.color(139, 0, 139)

    /**
     * #556B2F
     * <div style="width:120px;height:120px;background-color:#556B2F"></div>
     */
    val DARKOLIVEGREEN: TextColor = TextColor.color(85, 107, 47)

    /**
     * #FF8C00
     * <div style="width:120px;height:120px;background-color:#FF8C00"></div>
     */
    val DARKORANGE: TextColor = TextColor.color(255, 140, 0)

    /**
     * #9932CC
     * <div style="width:120px;height:120px;background-color:#9932CC"></div>
     */
    val DARKORCHID: TextColor = TextColor.color(153, 50, 204)

    /**
     * #E9967A
     * <div style="width:120px;height:120px;background-color:#E9967A"></div>
     */
    val DARKSALMON: TextColor = TextColor.color(233, 150, 122)

    /**
     * #8FBC8F
     * <div style="width:120px;height:120px;background-color:#8FBC8F"></div>
     */
    val DARKSEAGREEN: TextColor = TextColor.color(143, 188, 143)

    /**
     * #483D8B
     * <div style="width:120px;height:120px;background-color:#483D8B"></div>
     */
    val DARKSLATEBLUE: TextColor = TextColor.color(72, 61, 139)

    /**
     * #2F4F4F
     * <div style="width:120px;height:120px;background-color:#2F4F4F"></div>
     */
    val DARKSLATEGRAY: TextColor = TextColor.color(47, 79, 79)

    /**
     * #00CED1
     * <div style="width:120px;height:120px;background-color:#00CED1"></div>
     */
    val DARKTURQUOISE: TextColor = TextColor.color(0, 206, 209)

    /**
     * #9400D3
     * <div style="width:120px;height:120px;background-color:#9400D3"></div>
     */
    val DARKVIOLET: TextColor = TextColor.color(148, 0, 211)

    /**
     * #FF1493
     * <div style="width:120px;height:120px;background-color:#FF1493"></div>
     */
    val DEEPPINK: TextColor = TextColor.color(255, 20, 147)

    /**
     * #00BFFF
     * <div style="width:120px;height:120px;background-color:#00BFFF"></div>
     */
    val DEEPSKYBLUE: TextColor = TextColor.color(0, 191, 255)

    /**
     * #696969
     * <div style="width:120px;height:120px;background-color:#696969"></div>
     */
    val DIMGRAY: TextColor = TextColor.color(105, 105, 105)

    /**
     * #1E90FF
     * <div style="width:120px;height:120px;background-color:#1E90FF"></div>
     */
    val DODGERBLUE: TextColor = TextColor.color(30, 144, 255)

    /**
     * #B22222
     * <div style="width:120px;height:120px;background-color:#B22222"></div>
     */
    val FIREBRICK: TextColor = TextColor.color(178, 34, 34)

    /**
     * #FFFAF0
     * <div style="width:120px;height:120px;background-color:#FFFAF0"></div>
     */
    val FLORALWHITE: TextColor = TextColor.color(255, 250, 240)

    /**
     * #228B22
     * <div style="width:120px;height:120px;background-color:#228B22"></div>
     */
    val FORESTGREEN: TextColor = TextColor.color(34, 139, 34)

    /**
     * #FF00FF
     * <div style="width:120px;height:120px;background-color:#FF00FF"></div>
     */
    val FUCHSIA: TextColor = TextColor.color(255, 0, 255)

    /**
     * #DCDCDC
     * <div style="width:120px;height:120px;background-color:#DCDCDC"></div>
     */
    val GAINSBORO: TextColor = TextColor.color(220, 220, 220)

    /**
     * #F8F8FF
     * <div style="width:120px;height:120px;background-color:#F8F8FF"></div>
     */
    val GHOSTWHITE: TextColor = TextColor.color(248, 248, 255)

    /**
     * #DAA520
     * <div style="width:120px;height:120px;background-color:#DAA520"></div>
     */
    val GOLDENROD: TextColor = TextColor.color(218, 165, 32)

    /**
     * #ADFF2F
     * <div style="width:120px;height:120px;background-color:#ADFF2F"></div>
     */
    val GREENYELLOW: TextColor = TextColor.color(173, 255, 47)

    /**
     * #F0FFF0
     * <div style="width:120px;height:120px;background-color:#F0FFF0"></div>
     */
    val HONEYDEW: TextColor = TextColor.color(240, 255, 240)

    /**
     * #FF69B4
     * <div style="width:120px;height:120px;background-color:#FF69B4"></div>
     */
    val HOTPINK: TextColor = TextColor.color(255, 105, 180)

    /**
     * #CD5C5C
     * <div style="width:120px;height:120px;background-color:#CD5C5C"></div>
     */
    val INDIANRED: TextColor = TextColor.color(205, 92, 92)

    /**
     * #4B0082
     * <div style="width:120px;height:120px;background-color:#4B0082"></div>
     */
    val INDIGO: TextColor = TextColor.color(75, 0, 130)

    /**
     * #FFFFF0
     * <div style="width:120px;height:120px;background-color:#FFFFF0"></div>
     */
    val IVORY: TextColor = TextColor.color(255, 255, 240)

    /**
     * #F0E68C
     * <div style="width:120px;height:120px;background-color:#F0E68C"></div>
     */
    val KHAKI: TextColor = TextColor.color(240, 230, 140)

    /**
     * #E6E6FA
     * <div style="width:120px;height:120px;background-color:#E6E6FA"></div>
     */
    val LAVENDER: TextColor = TextColor.color(230, 230, 250)

    /**
     * #FFF0F5
     * <div style="width:120px;height:120px;background-color:#FFF0F5"></div>
     */
    val LAVENDERBLUSH: TextColor = TextColor.color(255, 240, 245)

    /**
     * #7CFC00
     * <div style="width:120px;height:120px;background-color:#7CFC00"></div>
     */
    val LAWNGREEN: TextColor = TextColor.color(124, 252, 0)

    /**
     * #FFFACD
     * <div style="width:120px;height:120px;background-color:#FFFACD"></div>
     */
    val LEMONCHIFFON: TextColor = TextColor.color(255, 250, 205)

    /**
     * #ADD8E6
     * <div style="width:120px;height:120px;background-color:#ADD8E6"></div>
     */
    val LIGHTBLUE: TextColor = TextColor.color(173, 216, 230)

    /**
     * #F08080
     * <div style="width:120px;height:120px;background-color:#F08080"></div>
     */
    val LIGHTCORAL: TextColor = TextColor.color(240, 128, 128)

    /**
     * #E0FFFF
     * <div style="width:120px;height:120px;background-color:#E0FFFF"></div>
     */
    val LIGHTCYAN: TextColor = TextColor.color(224, 255, 255)

    /**
     * #FAFAD2
     * <div style="width:120px;height:120px;background-color:#FAFAD2"></div>
     */
    val LIGHTGOLDENRODYELLOW: TextColor = TextColor.color(250, 250, 210)

    /**
     * #D3D3D3
     * <div style="width:120px;height:120px;background-color:#D3D3D3"></div>
     */
    val LIGHTGRAY: TextColor = TextColor.color(211, 211, 211)

    /**
     * #90EE90
     * <div style="width:120px;height:120px;background-color:#90EE90"></div>
     */
    val LIGHTGREEN: TextColor = TextColor.color(144, 238, 144)

    /**
     * #FFB6C1
     * <div style="width:120px;height:120px;background-color:#FFB6C1"></div>
     */
    val LIGHTPINK: TextColor = TextColor.color(255, 182, 193)

    /**
     * #FFA07A
     * <div style="width:120px;height:120px;background-color:#FFA07A"></div>
     */
    val LIGHTSALMON: TextColor = TextColor.color(255, 160, 122)

    /**
     * #20B2AA
     * <div style="width:120px;height:120px;background-color:#20B2AA"></div>
     */
    val LIGHTSEAGREEN: TextColor = TextColor.color(32, 178, 170)

    /**
     * #87CEFA
     * <div style="width:120px;height:120px;background-color:#87CEFA"></div>
     */
    val LIGHTSKYBLUE: TextColor = TextColor.color(135, 206, 250)

    /**
     * #778899
     * <div style="width:120px;height:120px;background-color:#778899"></div>
     */
    val LIGHTSLATEGRAY: TextColor = TextColor.color(119, 136, 153)

    /**
     * #B0C4DE
     * <div style="width:120px;height:120px;background-color:#B0C4DE"></div>
     */
    val LIGHTSTEELBLUE: TextColor = TextColor.color(176, 196, 222)

    /**
     * #FFFFE0
     * <div style="width:120px;height:120px;background-color:#FFFFE0"></div>
     */
    val LIGHTYELLOW: TextColor = TextColor.color(255, 255, 224)

    /**
     * #00FF00
     * <div style="width:120px;height:120px;background-color:#00FF00"></div>
     */
    val LIME: TextColor = TextColor.color(0, 255, 0)

    /**
     * #32CD32
     * <div style="width:120px;height:120px;background-color:#32CD32"></div>
     */
    val LIMEGREEN: TextColor = TextColor.color(50, 205, 50)

    /**
     * #FAF0E6
     * <div style="width:120px;height:120px;background-color:#FAF0E6"></div>
     */
    val LINEN: TextColor = TextColor.color(250, 240, 230)

    /**
     * #FF00FF
     * <div style="width:120px;height:120px;background-color:#FF00FF"></div>
     */
    val MAGENTA: TextColor = TextColor.color(255, 0, 255)

    /**
     * #800000
     * <div style="width:120px;height:120px;background-color:#800000"></div>
     */
    val MAROON: TextColor = TextColor.color(128, 0, 0)

    /**
     * #66CDAA
     * <div style="width:120px;height:120px;background-color:#66CDAA"></div>
     */
    val MEDIUMAQUAMARINE: TextColor = TextColor.color(102, 205, 170)

    /**
     * #0000CD
     * <div style="width:120px;height:120px;background-color:#0000CD"></div>
     */
    val MEDIUMBLUE: TextColor = TextColor.color(0, 0, 205)

    /**
     * #BA55D3
     * <div style="width:120px;height:120px;background-color:#BA55D3"></div>
     */
    val MEDIUMORCHID: TextColor = TextColor.color(186, 85, 211)

    /**
     * #9370DB
     * <div style="width:120px;height:120px;background-color:#9370DB"></div>
     */
    val MEDIUMPURPLE: TextColor = TextColor.color(147, 112, 219)

    /**
     * #3CB371
     * <div style="width:120px;height:120px;background-color:#3CB371"></div>
     */
    val MEDIUMSEAGREEN: TextColor = TextColor.color(60, 179, 113)

    /**
     * #7B68EE
     * <div style="width:120px;height:120px;background-color:#7B68EE"></div>
     */
    val MEDIUMSLATEBLUE: TextColor = TextColor.color(123, 104, 238)

    /**
     * #00FA9A
     * <div style="width:120px;height:120px;background-color:#00FA9A"></div>
     */
    val MEDIUMSPRINGGREEN: TextColor = TextColor.color(0, 250, 154)

    /**
     * #48D1CC
     * <div style="width:120px;height:120px;background-color:#48D1CC"></div>
     */
    val MEDIUMTURQUOISE: TextColor = TextColor.color(72, 209, 204)

    /**
     * #C71585
     * <div style="width:120px;height:120px;background-color:#C71585"></div>
     */
    val MEDIUMVIOLETRED: TextColor = TextColor.color(199, 21, 133)

    /**
     * #191970
     * <div style="width:120px;height:120px;background-color:#191970"></div>
     */
    val MIDNIGHTBLUE: TextColor = TextColor.color(25, 25, 112)

    /**
     * #F5FFFA
     * <div style="width:120px;height:120px;background-color:#F5FFFA"></div>
     */
    val MINTCREAM: TextColor = TextColor.color(245, 255, 250)

    /**
     * #FFE4E1
     * <div style="width:120px;height:120px;background-color:#FFE4E1"></div>
     */
    val MISTYROSE: TextColor = TextColor.color(255, 228, 225)

    /**
     * #FFE4B5
     * <div style="width:120px;height:120px;background-color:#FFE4B5"></div>
     */
    val MOCCASIN: TextColor = TextColor.color(255, 228, 181)

    /**
     * #FFDEAD
     * <div style="width:120px;height:120px;background-color:#FFDEAD"></div>
     */
    val NAVAJOWHITE: TextColor = TextColor.color(255, 222, 173)

    /**
     * #000080
     * <div style="width:120px;height:120px;background-color:#000080"></div>
     */
    val NAVY: TextColor = TextColor.color(0, 0, 128)

    /**
     * #FDF5E6
     * <div style="width:120px;height:120px;background-color:#FDF5E6"></div>
     */
    val OLDLACE: TextColor = TextColor.color(253, 245, 230)

    /**
     * #808000
     * <div style="width:120px;height:120px;background-color:#808000"></div>
     */
    val OLIVE: TextColor = TextColor.color(128, 128, 0)

    /**
     * #6B8E23
     * <div style="width:120px;height:120px;background-color:#6B8E23"></div>
     */
    val OLIVEDRAB: TextColor = TextColor.color(107, 142, 35)

    /**
     * #FFA500
     * <div style="width:120px;height:120px;background-color:#FFA500"></div>
     */
    val ORANGE: TextColor = TextColor.color(255, 165, 0)

    /**
     * #FF4500
     * <div style="width:120px;height:120px;background-color:#FF4500"></div>
     */
    val ORANGERED: TextColor = TextColor.color(255, 69, 0)

    /**
     * #DA70D6
     * <div style="width:120px;height:120px;background-color:#DA70D6"></div>
     */
    val ORCHID: TextColor = TextColor.color(218, 112, 214)

    /**
     * #EEE8AA
     * <div style="width:120px;height:120px;background-color:#EEE8AA"></div>
     */
    val PALEGOLDENROD: TextColor = TextColor.color(238, 232, 170)

    /**
     * #98FB98
     * <div style="width:120px;height:120px;background-color:#98FB98"></div>
     */
    val PALEGREEN: TextColor = TextColor.color(152, 251, 152)

    /**
     * #AFEEEE
     * <div style="width:120px;height:120px;background-color:#AFEEEE"></div>
     */
    val PALETURQUOISE: TextColor = TextColor.color(175, 238, 238)

    /**
     * #DB7093
     * <div style="width:120px;height:120px;background-color:#DB7093"></div>
     */
    val PALEVIOLETRED: TextColor = TextColor.color(219, 112, 147)

    /**
     * #FFEFD5
     * <div style="width:120px;height:120px;background-color:#FFEFD5"></div>
     */
    val PAPAYAWHIP: TextColor = TextColor.color(255, 239, 213)

    /**
     * #FFDAB9
     * <div style="width:120px;height:120px;background-color:#FFDAB9"></div>
     */
    val PEACHPUFF: TextColor = TextColor.color(255, 218, 185)

    /**
     * #CD853F
     * <div style="width:120px;height:120px;background-color:#CD853F"></div>
     */
    val PERU: TextColor = TextColor.color(205, 133, 63)

    /**
     * #FFC0CB
     * <div style="width:120px;height:120px;background-color:#FFC0CB"></div>
     */
    val PINK: TextColor = TextColor.color(255, 192, 203)

    /**
     * #DDA0DD
     * <div style="width:120px;height:120px;background-color:#DDA0DD"></div>
     */
    val PLUM: TextColor = TextColor.color(221, 160, 221)

    /**
     * #B0E0E6
     * <div style="width:120px;height:120px;background-color:#B0E0E6"></div>
     */
    val POWDERBLUE: TextColor = TextColor.color(176, 224, 230)

    /**
     * #800080
     * <div style="width:120px;height:120px;background-color:#800080"></div>
     */
    val PURPLE: TextColor = TextColor.color(128, 0, 128)

    /**
     * #BC8F8F
     * <div style="width:120px;height:120px;background-color:#BC8F8F"></div>
     */
    val ROSYBROWN: TextColor = TextColor.color(188, 143, 143)

    /**
     * #4169E1
     * <div style="width:120px;height:120px;background-color:#4169E1"></div>
     */
    val ROYALBLUE: TextColor = TextColor.color(65, 105, 225)

    /**
     * #8B4513
     * <div style="width:120px;height:120px;background-color:#8B4513"></div>
     */
    val SADDLEBROWN: TextColor = TextColor.color(139, 69, 19)

    /**
     * #FA8072
     * <div style="width:120px;height:120px;background-color:#FA8072"></div>
     */
    val SALMON: TextColor = TextColor.color(250, 128, 114)

    /**
     * #F4A460
     * <div style="width:120px;height:120px;background-color:#F4A460"></div>
     */
    val SANDYBROWN: TextColor = TextColor.color(244, 164, 96)

    /**
     * #2E8B57
     * <div style="width:120px;height:120px;background-color:#2E8B57"></div>
     */
    val SEAGREEN: TextColor = TextColor.color(46, 139, 87)

    /**
     * #FFF5EE
     * <div style="width:120px;height:120px;background-color:#FFF5EE"></div>
     */
    val SEASHELL: TextColor = TextColor.color(255, 245, 238)

    /**
     * #A0522D
     * <div style="width:120px;height:120px;background-color:#A0522D"></div>
     */
    val SIENNA: TextColor = TextColor.color(160, 82, 45)

    /**
     * #C0C0C0
     * <div style="width:120px;height:120px;background-color:#C0C0C0"></div>
     */
    val SILVER: TextColor = TextColor.color(192, 192, 192)

    /**
     * #87CEEB
     * <div style="width:120px;height:120px;background-color:#87CEEB"></div>
     */
    val SKYBLUE: TextColor = TextColor.color(135, 206, 235)

    /**
     * #6A5ACD
     * <div style="width:120px;height:120px;background-color:#6A5ACD"></div>
     */
    val SLATEBLUE: TextColor = TextColor.color(106, 90, 205)

    /**
     * #708090
     * <div style="width:120px;height:120px;background-color:#708090"></div>
     */
    val SLATEGRAY: TextColor = TextColor.color(112, 128, 144)

    /**
     * #FFFAFA
     * <div style="width:120px;height:120px;background-color:#FFFAFA"></div>
     */
    val SNOW: TextColor = TextColor.color(255, 250, 250)

    /**
     * #00FF7F
     * <div style="width:120px;height:120px;background-color:#00FF7F"></div>
     */
    val SPRINGGREEN: TextColor = TextColor.color(0, 255, 127)

    /**
     * #4682B4
     * <div style="width:120px;height:120px;background-color:#4682B4"></div>
     */
    val STEELBLUE: TextColor = TextColor.color(70, 130, 180)

    /**
     * #D2B48C
     * <div style="width:120px;height:120px;background-color:#D2B48C"></div>
     */
    val TAN: TextColor = TextColor.color(210, 180, 140)

    /**
     * #008080
     * <div style="width:120px;height:120px;background-color:#008080"></div>
     */
    val TEAL: TextColor = TextColor.color(0, 128, 128)

    /**
     * #D8BFD8
     * <div style="width:120px;height:120px;background-color:#D8BFD8"></div>
     */
    val THISTLE: TextColor = TextColor.color(216, 191, 216)

    /**
     * #FF6347
     * <div style="width:120px;height:120px;background-color:#FF6347"></div>
     */
    val TOMATO: TextColor = TextColor.color(255, 99, 71)

    /**
     * #40E0D0
     * <div style="width:120px;height:120px;background-color:#40E0D0"></div>
     */
    val TURQUOISE: TextColor = TextColor.color(64, 224, 208)

    /**
     * #EE82EE
     * <div style="width:120px;height:120px;background-color:#EE82EE"></div>
     */
    val VIOLET: TextColor = TextColor.color(238, 130, 238)

    /**
     * #F5DEB3
     * <div style="width:120px;height:120px;background-color:#F5DEB3"></div>
     */
    val WHEAT: TextColor = TextColor.color(245, 222, 179)

    /**
     * #F5F5F5
     * <div style="width:120px;height:120px;background-color:#F5F5F5"></div>
     */
    val WHITESMOKE: TextColor = TextColor.color(245, 245, 245)

    /**
     * #9ACD32
     * <div style="width:120px;height:120px;background-color:#9ACD32"></div>
     */
    val YELLOWGREEN: TextColor = TextColor.color(154, 205, 50)

    fun allColors(): List<TextColor?> {
        return Arrays.stream(KColors::class.java.fields)
            .filter { field: Field -> field.type == TextColor::class.java }
            .map { field: Field ->
                try {
                    return@map field[null] as TextColor
                } catch (ignored: IllegalArgumentException) {
                    return@map null
                } catch (ignored: IllegalAccessException) {
                    return@map null
                }
            }
            .filter { obj: TextColor? -> Objects.nonNull(obj) }
            .collect(Collectors.toList())
    }

    fun allDecorations(): List<TextDecoration?> {
        return Arrays.stream(KColors::class.java.fields)
            .filter { field: Field -> field.type == TextDecoration::class.java }
            .map { field: Field ->
                try {
                    return@map field[null] as TextDecoration
                } catch (ignored: IllegalArgumentException) {
                    return@map null
                } catch (ignored: IllegalAccessException) {
                    return@map null
                }
            }
            .filter { obj: TextDecoration? -> Objects.nonNull(obj) }
            .collect(Collectors.toList())
    }
}
