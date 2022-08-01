@file:Suppress("Unused")

package net.axay.kspigot.extensions

import net.axay.kspigot.main.PluginInstance
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.World
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Shortcut to get all online players.
 * @see Bukkit.getOnlinePlayers
 */
val onlinePlayers: Collection<Player> get() = Bukkit.getOnlinePlayers()

/**
 * Shortcut to get a collection of all
 * online players plus the console command sender.
 */
val onlineSenders: Collection<CommandSender> get() = Bukkit.getOnlinePlayers().plus(Bukkit.getConsoleSender())

/**
 * Shortcut to get the Server.
 * @see Bukkit.getServer
 */
val server get() = Bukkit.getServer()

/**
 * Shortcut to get the PluginManager.
 * @see Bukkit.getPluginManager
 */
val pluginManager get() = Bukkit.getPluginManager()

/**
 * Broadcasts a message ([msg]) on the server.
 * @return the number of recipients
 * @see Bukkit.broadcastMessage
 */
fun broadcast(msg: String) = Bukkit.getServer().broadcast(text(msg))

/**
 * Broadcasts a message ([msg]) on the server.
 * @return the number of recipients
 * @see Bukkit.broadcastMessage
 */
fun broadcast(msg: Component) = Bukkit.getServer().broadcast(msg)

/**
 * Shortcut to get the ConsoleSender.
 * @see Bukkit.getConsoleSender
 */
val console get() = Bukkit.getConsoleSender()

/**
 * Shortcut for creating a new [NamespacedKey]
 */
fun pluginKey(key: String) = NamespacedKey(PluginInstance, key)

/**
 * Shortcut to get a collection of all worlds
 */
val worlds: List<World> get() = Bukkit.getWorlds()

/**
 * Enum you need for the centeredMessage function
 */
enum class DefaultFontInfo(var char: Char, var length: Int){
  A('A', 5),
  a('a', 5),
  B('B', 5),
  b('b', 5),
  C('C', 5),
  c('c', 5),
  D('D', 5),
  d('d', 5),
  E('E', 5),
  e('e', 5),
  F('F', 5),
  f('f', 4),
  G('G', 5),
  g('g', 5),
  H('H', 5),
  h('h', 5),
  I('I', 3),
  i('i', 1),
  J('J', 5),
  j('j', 5),
  K('K', 5),
  k('k', 4),
  L('L', 5),
  l('l', 1),
  M('M', 5),
  m('m', 5),
  N('N', 5),
  n('n', 5),
  O('O', 5),
  o('o', 5),
  P('P', 5),
  p('p', 5),
  Q('Q', 5),
  q('q', 5),
  R('R', 5),
  r('r', 5),
  S('S', 5),
  s('s', 5),
  T('T', 5),
  t('t', 4),
  U('U', 5),
  u('u', 5),
  V('V', 5),
  v('v', 5),
  W('W', 5),
  w('w', 5),
  X('X', 5),
  x('x', 5),
  Y('Y', 5),
  y('y', 5),
  Z('Z', 5),
  z('z', 5),
  NUM_1('1', 5),
  NUM_2('2', 5),
  NUM_3('3', 5),
  NUM_4('4', 5),
  NUM_5('5', 5),
  NUM_6('6', 5),
  NUM_7('7', 5),
  NUM_8('8', 5),
  NUM_9('9', 5),
  NUM_0('0', 5),
  EXCLAMATION_POINT('!', 1),
  AT_SYMBOL('@', 6),
  NUM_SIGN('#', 5),
  DOLLAR_SIGN('$', 5),
  PERCENT('%', 5),
  UP_ARROW('^', 5),
  AMPERSAND('&', 5),
  ASTERISK('*', 5),
  LEFT_PARENTHESIS('(', 4),
  RIGHT_PERENTHESIS(')', 4),
  MINUS('-', 5),
  UNDERSCORE('_', 5),
  PLUS_SIGN('+', 5),
  EQUALS_SIGN('=', 5),
  LEFT_CURL_BRACE('{', 4),
  RIGHT_CURL_BRACE('}', 4),
  LEFT_BRACKET('[', 3),
  RIGHT_BRACKET(']', 3),
  COLON(':', 1),
  SEMI_COLON(';', 1),
  DOUBLE_QUOTE('"', 3),
  SINGLE_QUOTE('\'', 1),
  LEFT_ARROW('<', 4),
  RIGHT_ARROW('>', 4),
  QUESTION_MARK('?', 5),
  SLASH('/', 5),
  BACK_SLASH('\\', 5),
  LINE('|', 1),
  TILDE('~', 5),
  TICK('`', 2),
  PERIOD('.', 1),
  COMMA(',', 1),
  SPACE(' ', 3),
  DEFAULT('a', 4);

  companion object{
    fun getDefaultFontInfo(char: Char): DefaultFontInfo {
      for(d in values()){
        if(d.char == char)return d
      }
      return DEFAULT
    }
  }

  fun boldLength(): Int {
    if(this == SPACE)return length
    return length+1
  }
}

/**
 * Center a message
 * Only works for one line!
 * @return Component
 * @param lines Content lines which will centered
 */
fun Player.sendCenteredMessage(lines: Array<String>){
  for(content in lines){
    val center_px = 154
    var message_px = 0
    var previousCode = false
    var isBold = false
    for(c in content.toCharArray()){
      if(c == '§'){
        previousCode = true
        continue
      }else if(previousCode){
        previousCode = false
        if(c.lowercaseChar() == 'l'){
          isBold = true
          continue
        }else isBold = false
      }else{
        val defaultFontInfo = DefaultFontInfo.getDefaultFontInfo(c)
        message_px += if (isBold) defaultFontInfo.boldLength() else defaultFontInfo.length
        message_px++
      }
    }

    val halvedMessageSize: Int = message_px / 2
    val toCompensate: Int = center_px - halvedMessageSize
    val spaceLength = DefaultFontInfo.SPACE.length + 1
    var compensated = 0
    val sb = StringBuilder()
    while (compensated < toCompensate) {
      sb.append(" ")
      compensated += spaceLength
    }
    player?.sendMessage(sb.toString() + content)
  }
}

/**
 * Center a message
 * Only works for one line!
 * @return Component
 * @param content Content which will centered
 */
fun Player.sendCenteredMessage(content: String) {
  val lineSeparator = System.getProperty("line.separator")
  if(content.contains(lineSeparator)){
    sendCenteredMessage(content.split(lineSeparator).toTypedArray())
    return
  }

  sendCenteredMessage(arrayOf(content))
}
