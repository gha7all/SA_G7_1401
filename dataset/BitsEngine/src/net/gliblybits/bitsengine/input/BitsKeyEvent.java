
/*
 * Copyright 2013 Marc Wiedenhoeft - GliblyBits
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.gliblybits.bitsengine.input;

public final class BitsKeyEvent {
    /** Key code constant: Unknown key code. */
    public static final int KEY_UNKNOWN         = 0;
    /** Key code constant: Soft Left key.
     * Usually situated below the display on phones and used as a multi-function
     * feature key for selecting a software defined function shown on the bottom left
     * of the display. */
    public static final int KEY_SOFT_LEFT       = 1;
    /** Key code constant: Soft Right key.
     * Usually situated below the display on phones and used as a multi-function
     * feature key for selecting a software defined function shown on the bottom right
     * of the display. */
    public static final int KEY_SOFT_RIGHT      = 2;
    /** Key code constant: Home key.
     * This key is handled by the framework and is never delivered to applications. */
    public static final int KEY_HOME            = 3;
    /** Key code constant: Back key. */
    public static final int KEY_BACK            = 4;
    /** Key code constant: Call key. */
    public static final int KEY_CALL            = 5;
    /** Key code constant: End Call key. */
    public static final int KEY_ENDCALL         = 6;
    /** Key code constant: '0' key. */
    public static final int KEY_0               = 7;
    /** Key code constant: '1' key. */
    public static final int KEY_1               = 8;
    /** Key code constant: '2' key. */
    public static final int KEY_2               = 9;
    /** Key code constant: '3' key. */
    public static final int KEY_3               = 10;
    /** Key code constant: '4' key. */
    public static final int KEY_4               = 11;
    /** Key code constant: '5' key. */
    public static final int KEY_5               = 12;
    /** Key code constant: '6' key. */
    public static final int KEY_6               = 13;
    /** Key code constant: '7' key. */
    public static final int KEY_7               = 14;
    /** Key code constant: '8' key. */
    public static final int KEY_8               = 15;
    /** Key code constant: '9' key. */
    public static final int KEY_9               = 16;
    /** Key code constant: '*' key. */
    public static final int KEY_STAR            = 17;
    /** Key code constant: '#' key. */
    public static final int KEY_POUND           = 18;
    /** Key code constant: Directional Pad Up key.
     * May also be synthesized from trackball motions. */
    public static final int KEY_DPAD_UP         = 19;
    /** Key code constant: Directional Pad Down key.
     * May also be synthesized from trackball motions. */
    public static final int KEY_DPAD_DOWN       = 20;
    /** Key code constant: Directional Pad Left key.
     * May also be synthesized from trackball motions. */
    public static final int KEY_DPAD_LEFT       = 21;
    /** Key code constant: Directional Pad Right key.
     * May also be synthesized from trackball motions. */
    public static final int KEY_DPAD_RIGHT      = 22;
    /** Key code constant: Directional Pad Center key.
     * May also be synthesized from trackball motions. */
    public static final int KEY_DPAD_CENTER     = 23;
    /** Key code constant: Volume Up key.
     * Adjusts the speaker volume up. */
    public static final int KEY_VOLUME_UP       = 24;
    /** Key code constant: Volume Down key.
     * Adjusts the speaker volume down. */
    public static final int KEY_VOLUME_DOWN     = 25;
    /** Key code constant: Power key. */
    public static final int KEY_POWER           = 26;
    /** Key code constant: Camera key.
     * Used to launch a camera application or take pictures. */
    public static final int KEY_CAMERA          = 27;
    /** Key code constant: Clear key. */
    public static final int KEY_CLEAR           = 28;
    /** Key code constant: 'A' key. */
    public static final int KEY_A               = 29;
    /** Key code constant: 'B' key. */
    public static final int KEY_B               = 30;
    /** Key code constant: 'C' key. */
    public static final int KEY_C               = 31;
    /** Key code constant: 'D' key. */
    public static final int KEY_D               = 32;
    /** Key code constant: 'E' key. */
    public static final int KEY_E               = 33;
    /** Key code constant: 'F' key. */
    public static final int KEY_F               = 34;
    /** Key code constant: 'G' key. */
    public static final int KEY_G               = 35;
    /** Key code constant: 'H' key. */
    public static final int KEY_H               = 36;
    /** Key code constant: 'I' key. */
    public static final int KEY_I               = 37;
    /** Key code constant: 'J' key. */
    public static final int KEY_J               = 38;
    /** Key code constant: 'K' key. */
    public static final int KEY_K               = 39;
    /** Key code constant: 'L' key. */
    public static final int KEY_L               = 40;
    /** Key code constant: 'M' key. */
    public static final int KEY_M               = 41;
    /** Key code constant: 'N' key. */
    public static final int KEY_N               = 42;
    /** Key code constant: 'O' key. */
    public static final int KEY_O               = 43;
    /** Key code constant: 'P' key. */
    public static final int KEY_P               = 44;
    /** Key code constant: 'Q' key. */
    public static final int KEY_Q               = 45;
    /** Key code constant: 'R' key. */
    public static final int KEY_R               = 46;
    /** Key code constant: 'S' key. */
    public static final int KEY_S               = 47;
    /** Key code constant: 'T' key. */
    public static final int KEY_T               = 48;
    /** Key code constant: 'U' key. */
    public static final int KEY_U               = 49;
    /** Key code constant: 'V' key. */
    public static final int KEY_V               = 50;
    /** Key code constant: 'W' key. */
    public static final int KEY_W               = 51;
    /** Key code constant: 'X' key. */
    public static final int KEY_X               = 52;
    /** Key code constant: 'Y' key. */
    public static final int KEY_Y               = 53;
    /** Key code constant: 'Z' key. */
    public static final int KEY_Z               = 54;
    /** Key code constant: ',' key. */
    public static final int KEY_COMMA           = 55;
    /** Key code constant: '.' key. */
    public static final int KEY_PERIOD          = 56;
    /** Key code constant: Left Alt modifier key. */
    public static final int KEY_ALT_LEFT        = 57;
    /** Key code constant: Right Alt modifier key. */
    public static final int KEY_ALT_RIGHT       = 58;
    /** Key code constant: Left Shift modifier key. */
    public static final int KEY_SHIFT_LEFT      = 59;
    /** Key code constant: Right Shift modifier key. */
    public static final int KEY_SHIFT_RIGHT     = 60;
    /** Key code constant: Tab key. */
    public static final int KEY_TAB             = 61;
    /** Key code constant: Space key. */
    public static final int KEY_SPACE           = 62;
    /** Key code constant: Symbol modifier key.
     * Used to enter alternate symbols. */
    public static final int KEY_SYM             = 63;
    /** Key code constant: Explorer special function key.
     * Used to launch a browser application. */
    public static final int KEY_EXPLORER        = 64;
    /** Key code constant: Envelope special function key.
     * Used to launch a mail application. */
    public static final int KEY_ENVELOPE        = 65;
    /** Key code constant: Enter key. */
    public static final int KEY_ENTER           = 66;
    /** Key code constant: Backspace key.
     * Deletes characters before the insertion point, unlike {@link #KEY_FORWARD_DEL}. */
    public static final int KEY_DEL             = 67;
    /** Key code constant: '`' (backtick) key. */
    public static final int KEY_GRAVE           = 68;
    /** Key code constant: '-'. */
    public static final int KEY_MINUS           = 69;
    /** Key code constant: '=' key. */
    public static final int KEY_EQUALS          = 70;
    /** Key code constant: '[' key. */
    public static final int KEY_LEFT_BRACKET    = 71;
    /** Key code constant: ']' key. */
    public static final int KEY_RIGHT_BRACKET   = 72;
    /** Key code constant: '\' key. */
    public static final int KEY_BACKSLASH       = 73;
    /** Key code constant: ';' key. */
    public static final int KEY_SEMICOLON       = 74;
    /** Key code constant: ''' (apostrophe) key. */
    public static final int KEY_APOSTROPHE      = 75;
    /** Key code constant: '/' key. */
    public static final int KEY_SLASH           = 76;
    /** Key code constant: '@' key. */
    public static final int KEY_AT              = 77;
    /** Key code constant: Number modifier key.
     * Used to enter numeric symbols.
     * This key is not Num Lock; it is more like {@link #KEY_ALT_LEFT} and is
     * interpreted as an ALT key by {@link android.text.method.MetaKeyKeyListener}. */
    public static final int KEY_NUM             = 78;
    /** Key code constant: Headset Hook key.
     * Used to hang up calls and stop media. */
    public static final int KEY_HEADSETHOOK     = 79;
    /** Key code constant: Camera Focus key.
     * Used to focus the camera. */
    public static final int KEY_FOCUS           = 80;   // *Camera* focus
    /** Key code constant: '+' key. */
    public static final int KEY_PLUS            = 81;
    /** Key code constant: Menu key. */
    public static final int KEY_MENU            = 82;
    /** Key code constant: Notification key. */
    public static final int KEY_NOTIFICATION    = 83;
    /** Key code constant: Search key. */
    public static final int KEY_SEARCH          = 84;
    /** Key code constant: Play/Pause media key. */
    public static final int KEY_MEDIA_PLAY_PAUSE= 85;
    /** Key code constant: Stop media key. */
    public static final int KEY_MEDIA_STOP      = 86;
    /** Key code constant: Play Next media key. */
    public static final int KEY_MEDIA_NEXT      = 87;
    /** Key code constant: Play Previous media key. */
    public static final int KEY_MEDIA_PREVIOUS  = 88;
    /** Key code constant: Rewind media key. */
    public static final int KEY_MEDIA_REWIND    = 89;
    /** Key code constant: Fast Forward media key. */
    public static final int KEY_MEDIA_FAST_FORWARD = 90;
    /** Key code constant: Mute key.
     * Mutes the microphone, unlike {@link #KEY_VOLUME_MUTE}. */
    public static final int KEY_MUTE            = 91;
    /** Key code constant: Page Up key. */
    public static final int KEY_PAGE_UP         = 92;
    /** Key code constant: Page Down key. */
    public static final int KEY_PAGE_DOWN       = 93;
    /** Key code constant: Picture Symbols modifier key.
     * Used to switch symbol sets (Emoji, Kao-moji). */
    public static final int KEY_PICTSYMBOLS     = 94;   // switch symbol-sets (Emoji,Kao-moji)
    /** Key code constant: Switch Charset modifier key.
     * Used to switch character sets (Kanji, Katakana). */
    public static final int KEY_SWITCH_CHARSET  = 95;   // switch char-sets (Kanji,Katakana)
    /** Key code constant: A Button key.
     * On a game controller, the A button should be either the button labeled A
     * or the first button on the upper row of controller buttons. */
    public static final int KEY_BUTTON_A        = 96;
    /** Key code constant: B Button key.
     * On a game controller, the B button should be either the button labeled B
     * or the second button on the upper row of controller buttons. */
    public static final int KEY_BUTTON_B        = 97;
    /** Key code constant: C Button key.
     * On a game controller, the C button should be either the button labeled C
     * or the third button on the upper row of controller buttons. */
    public static final int KEY_BUTTON_C        = 98;
    /** Key code constant: X Button key.
     * On a game controller, the X button should be either the button labeled X
     * or the first button on the lower row of controller buttons. */
    public static final int KEY_BUTTON_X        = 99;
    /** Key code constant: Y Button key.
     * On a game controller, the Y button should be either the button labeled Y
     * or the second button on the lower row of controller buttons. */
    public static final int KEY_BUTTON_Y        = 100;
    /** Key code constant: Z Button key.
     * On a game controller, the Z button should be either the button labeled Z
     * or the third button on the lower row of controller buttons. */
    public static final int KEY_BUTTON_Z        = 101;
    /** Key code constant: L1 Button key.
     * On a game controller, the L1 button should be either the button labeled L1 (or L)
     * or the top left trigger button. */
    public static final int KEY_BUTTON_L1       = 102;
    /** Key code constant: R1 Button key.
     * On a game controller, the R1 button should be either the button labeled R1 (or R)
     * or the top right trigger button. */
    public static final int KEY_BUTTON_R1       = 103;
    /** Key code constant: L2 Button key.
     * On a game controller, the L2 button should be either the button labeled L2
     * or the bottom left trigger button. */
    public static final int KEY_BUTTON_L2       = 104;
    /** Key code constant: R2 Button key.
     * On a game controller, the R2 button should be either the button labeled R2
     * or the bottom right trigger button. */
    public static final int KEY_BUTTON_R2       = 105;
    /** Key code constant: Left Thumb Button key.
     * On a game controller, the left thumb button indicates that the left (or only)
     * joystick is pressed. */
    public static final int KEY_BUTTON_THUMBL   = 106;
    /** Key code constant: Right Thumb Button key.
     * On a game controller, the right thumb button indicates that the right
     * joystick is pressed. */
    public static final int KEY_BUTTON_THUMBR   = 107;
    /** Key code constant: Start Button key.
     * On a game controller, the button labeled Start. */
    public static final int KEY_BUTTON_START    = 108;
    /** Key code constant: Select Button key.
     * On a game controller, the button labeled Select. */
    public static final int KEY_BUTTON_SELECT   = 109;
    /** Key code constant: Mode Button key.
     * On a game controller, the button labeled Mode. */
    public static final int KEY_BUTTON_MODE     = 110;
    /** Key code constant: Escape key. */
    public static final int KEY_ESCAPE          = 111;
    /** Key code constant: Forward Delete key.
     * Deletes characters ahead of the insertion point, unlike {@link #KEY_DEL}. */
    public static final int KEY_FORWARD_DEL     = 112;
    /** Key code constant: Left Control modifier key. */
    public static final int KEY_CTRL_LEFT       = 113;
    /** Key code constant: Right Control modifier key. */
    public static final int KEY_CTRL_RIGHT      = 114;
    /** Key code constant: Caps Lock key. */
    public static final int KEY_CAPS_LOCK       = 115;
    /** Key code constant: Scroll Lock key. */
    public static final int KEY_SCROLL_LOCK     = 116;
    /** Key code constant: Left Meta modifier key. */
    public static final int KEY_META_LEFT       = 117;
    /** Key code constant: Right Meta modifier key. */
    public static final int KEY_META_RIGHT      = 118;
    /** Key code constant: Function modifier key. */
    public static final int KEY_FUNCTION        = 119;
    /** Key code constant: System Request / Print Screen key. */
    public static final int KEY_SYSRQ           = 120;
    /** Key code constant: Break / Pause key. */
    public static final int KEY_BREAK           = 121;
    /** Key code constant: Home Movement key.
     * Used for scrolling or moving the cursor around to the start of a line
     * or to the top of a list. */
    public static final int KEY_MOVE_HOME       = 122;
    /** Key code constant: End Movement key.
     * Used for scrolling or moving the cursor around to the end of a line
     * or to the bottom of a list. */
    public static final int KEY_MOVE_END        = 123;
    /** Key code constant: Insert key.
     * Toggles insert / overwrite edit mode. */
    public static final int KEY_INSERT          = 124;
    /** Key code constant: Forward key.
     * Navigates forward in the history stack.  Complement of {@link #KEY_BACK}. */
    public static final int KEY_FORWARD         = 125;
    /** Key code constant: Play media key. */
    public static final int KEY_MEDIA_PLAY      = 126;
    /** Key code constant: Pause media key. */
    public static final int KEY_MEDIA_PAUSE     = 127;
    /** Key code constant: Close media key.
     * May be used to close a CD tray, for example. */
    public static final int KEY_MEDIA_CLOSE     = 128;
    /** Key code constant: Eject media key.
     * May be used to eject a CD tray, for example. */
    public static final int KEY_MEDIA_EJECT     = 129;
    /** Key code constant: Record media key. */
    public static final int KEY_MEDIA_RECORD    = 130;
    /** Key code constant: F1 key. */
    public static final int KEY_F1              = 131;
    /** Key code constant: F2 key. */
    public static final int KEY_F2              = 132;
    /** Key code constant: F3 key. */
    public static final int KEY_F3              = 133;
    /** Key code constant: F4 key. */
    public static final int KEY_F4              = 134;
    /** Key code constant: F5 key. */
    public static final int KEY_F5              = 135;
    /** Key code constant: F6 key. */
    public static final int KEY_F6              = 136;
    /** Key code constant: F7 key. */
    public static final int KEY_F7              = 137;
    /** Key code constant: F8 key. */
    public static final int KEY_F8              = 138;
    /** Key code constant: F9 key. */
    public static final int KEY_F9              = 139;
    /** Key code constant: F10 key. */
    public static final int KEY_F10             = 140;
    /** Key code constant: F11 key. */
    public static final int KEY_F11             = 141;
    /** Key code constant: F12 key. */
    public static final int KEY_F12             = 142;
    /** Key code constant: Num Lock key.
     * This is the Num Lock key; it is different from {@link #KEY_NUM}.
     * This key alters the behavior of other keys on the numeric keypad. */
    public static final int KEY_NUM_LOCK        = 143;
    /** Key code constant: Numeric keypad '0' key. */
    public static final int KEY_NUMPAD_0        = 144;
    /** Key code constant: Numeric keypad '1' key. */
    public static final int KEY_NUMPAD_1        = 145;
    /** Key code constant: Numeric keypad '2' key. */
    public static final int KEY_NUMPAD_2        = 146;
    /** Key code constant: Numeric keypad '3' key. */
    public static final int KEY_NUMPAD_3        = 147;
    /** Key code constant: Numeric keypad '4' key. */
    public static final int KEY_NUMPAD_4        = 148;
    /** Key code constant: Numeric keypad '5' key. */
    public static final int KEY_NUMPAD_5        = 149;
    /** Key code constant: Numeric keypad '6' key. */
    public static final int KEY_NUMPAD_6        = 150;
    /** Key code constant: Numeric keypad '7' key. */
    public static final int KEY_NUMPAD_7        = 151;
    /** Key code constant: Numeric keypad '8' key. */
    public static final int KEY_NUMPAD_8        = 152;
    /** Key code constant: Numeric keypad '9' key. */
    public static final int KEY_NUMPAD_9        = 153;
    /** Key code constant: Numeric keypad '/' key (for division). */
    public static final int KEY_NUMPAD_DIVIDE   = 154;
    /** Key code constant: Numeric keypad '*' key (for multiplication). */
    public static final int KEY_NUMPAD_MULTIPLY = 155;
    /** Key code constant: Numeric keypad '-' key (for subtraction). */
    public static final int KEY_NUMPAD_SUBTRACT = 156;
    /** Key code constant: Numeric keypad '+' key (for addition). */
    public static final int KEY_NUMPAD_ADD      = 157;
    /** Key code constant: Numeric keypad '.' key (for decimals or digit grouping). */
    public static final int KEY_NUMPAD_DOT      = 158;
    /** Key code constant: Numeric keypad ',' key (for decimals or digit grouping). */
    public static final int KEY_NUMPAD_COMMA    = 159;
    /** Key code constant: Numeric keypad Enter key. */
    public static final int KEY_NUMPAD_ENTER    = 160;
    /** Key code constant: Numeric keypad '=' key. */
    public static final int KEY_NUMPAD_EQUALS   = 161;
    /** Key code constant: Numeric keypad '(' key. */
    public static final int KEY_NUMPAD_LEFT_PAREN = 162;
    /** Key code constant: Numeric keypad ')' key. */
    public static final int KEY_NUMPAD_RIGHT_PAREN = 163;
    /** Key code constant: Volume Mute key.
     * Mutes the speaker, unlike {@link #KEY_MUTE}.
     * This key should normally be implemented as a toggle such that the first press
     * mutes the speaker and the second press restores the original volume. */
    public static final int KEY_VOLUME_MUTE     = 164;
    /** Key code constant: Info key.
     * Common on TV remotes to show additional information related to what is
     * currently being viewed. */
    public static final int KEY_INFO            = 165;
    /** Key code constant: Channel up key.
     * On TV remotes, increments the television channel. */
    public static final int KEY_CHANNEL_UP      = 166;
    /** Key code constant: Channel down key.
     * On TV remotes, decrements the television channel. */
    public static final int KEY_CHANNEL_DOWN    = 167;
    /** Key code constant: Zoom in key. */
    public static final int KEY_ZOOM_IN         = 168;
    /** Key code constant: Zoom out key. */
    public static final int KEY_ZOOM_OUT        = 169;
    /** Key code constant: TV key.
     * On TV remotes, switches to viewing live TV. */
    public static final int KEY_TV              = 170;
    /** Key code constant: Window key.
     * On TV remotes, toggles picture-in-picture mode or other windowing functions. */
    public static final int KEY_WINDOW          = 171;
    /** Key code constant: Guide key.
     * On TV remotes, shows a programming guide. */
    public static final int KEY_GUIDE           = 172;
    /** Key code constant: DVR key.
     * On some TV remotes, switches to a DVR mode for recorded shows. */
    public static final int KEY_DVR             = 173;
    /** Key code constant: Bookmark key.
     * On some TV remotes, bookmarks content or web pages. */
    public static final int KEY_BOOKMARK        = 174;
    /** Key code constant: Toggle captions key.
     * Switches the mode for closed-captioning text, for example during television shows. */
    public static final int KEY_CAPTIONS        = 175;
    /** Key code constant: Settings key.
     * Starts the system settings activity. */
    public static final int KEY_SETTINGS        = 176;
    /** Key code constant: TV power key.
     * On TV remotes, toggles the power on a television screen. */
    public static final int KEY_TV_POWER        = 177;
    /** Key code constant: TV input key.
     * On TV remotes, switches the input on a television screen. */
    public static final int KEY_TV_INPUT        = 178;
    /** Key code constant: Set-top-box power key.
     * On TV remotes, toggles the power on an external Set-top-box. */
    public static final int KEY_STB_POWER       = 179;
    /** Key code constant: Set-top-box input key.
     * On TV remotes, switches the input mode on an external Set-top-box. */
    public static final int KEY_STB_INPUT       = 180;
    /** Key code constant: A/V Receiver power key.
     * On TV remotes, toggles the power on an external A/V Receiver. */
    public static final int KEY_AVR_POWER       = 181;
    /** Key code constant: A/V Receiver input key.
     * On TV remotes, switches the input mode on an external A/V Receiver. */
    public static final int KEY_AVR_INPUT       = 182;
    /** Key code constant: Red "programmable" key.
     * On TV remotes, acts as a contextual/programmable key. */
    public static final int KEY_PROG_RED        = 183;
    /** Key code constant: Green "programmable" key.
     * On TV remotes, actsas a contextual/programmable key. */
    public static final int KEY_PROG_GREEN      = 184;
    /** Key code constant: Yellow "programmable" key.
     * On TV remotes, acts as a contextual/programmable key. */
    public static final int KEY_PROG_YELLOW     = 185;
    /** Key code constant: Blue "programmable" key.
     * On TV remotes, acts as a contextual/programmable key. */
    public static final int KEY_PROG_BLUE       = 186;
    /** Key code constant: App switch key.
     * Should bring up the application switcher dialog. */
    public static final int KEY_APP_SWITCH      = 187;
    /** Key code constant: Generic Game Pad Button #1.*/
    public static final int KEY_BUTTON_1        = 188;
    /** Key code constant: Generic Game Pad Button #2.*/
    public static final int KEY_BUTTON_2        = 189;
    /** Key code constant: Generic Game Pad Button #3.*/
    public static final int KEY_BUTTON_3        = 190;
    /** Key code constant: Generic Game Pad Button #4.*/
    public static final int KEY_BUTTON_4        = 191;
    /** Key code constant: Generic Game Pad Button #5.*/
    public static final int KEY_BUTTON_5        = 192;
    /** Key code constant: Generic Game Pad Button #6.*/
    public static final int KEY_BUTTON_6        = 193;
    /** Key code constant: Generic Game Pad Button #7.*/
    public static final int KEY_BUTTON_7        = 194;
    /** Key code constant: Generic Game Pad Button #8.*/
    public static final int KEY_BUTTON_8        = 195;
    /** Key code constant: Generic Game Pad Button #9.*/
    public static final int KEY_BUTTON_9        = 196;
    /** Key code constant: Generic Game Pad Button #10.*/
    public static final int KEY_BUTTON_10       = 197;
    /** Key code constant: Generic Game Pad Button #11.*/
    public static final int KEY_BUTTON_11       = 198;
    /** Key code constant: Generic Game Pad Button #12.*/
    public static final int KEY_BUTTON_12       = 199;
    /** Key code constant: Generic Game Pad Button #13.*/
    public static final int KEY_BUTTON_13       = 200;
    /** Key code constant: Generic Game Pad Button #14.*/
    public static final int KEY_BUTTON_14       = 201;
    /** Key code constant: Generic Game Pad Button #15.*/
    public static final int KEY_BUTTON_15       = 202;
    /** Key code constant: Generic Game Pad Button #16.*/
    public static final int KEY_BUTTON_16       = 203;
    /** Key code constant: Language Switch key.
     * Toggles the current input language such as switching between English and Japanese on
     * a QWERTY keyboard.  On some devices, the same function may be performed by
     * pressing Shift+Spacebar. */
    public static final int KEY_LANGUAGE_SWITCH = 204;
    /** Key code constant: Manner Mode key.
     * Toggles silent or vibrate mode on and off to make the device behave more politely
     * in certain settings such as on a crowded train.  On some devices, the key may only
     * operate when long-pressed. */
    public static final int KEY_MANNER_MODE     = 205;
    /** Key code constant: 3D Mode key.
     * Toggles the display between 2D and 3D mode. */
    public static final int KEY_3D_MODE         = 206;
    /** Key code constant: Contacts special function key.
     * Used to launch an address book application. */
    public static final int KEY_CONTACTS        = 207;
    /** Key code constant: Calendar special function key.
     * Used to launch a calendar application. */
    public static final int KEY_CALENDAR        = 208;
    /** Key code constant: Music special function key.
     * Used to launch a music player application. */
    public static final int KEY_MUSIC           = 209;
    /** Key code constant: Calculator special function key.
     * Used to launch a calculator application. */
    public static final int KEY_CALCULATOR      = 210;
    /** Key code constant: Japanese full-width / half-width key. */
    public static final int KEY_ZENKAKU_HANKAKU = 211;
    /** Key code constant: Japanese alphanumeric key. */
    public static final int KEY_EISU            = 212;
    /** Key code constant: Japanese non-conversion key. */
    public static final int KEY_MUHENKAN        = 213;
    /** Key code constant: Japanese conversion key. */
    public static final int KEY_HENKAN          = 214;
    /** Key code constant: Japanese katakana / hiragana key. */
    public static final int KEY_KATAKANA_HIRAGANA = 215;
    /** Key code constant: Japanese Yen key. */
    public static final int KEY_YEN             = 216;
    /** Key code constant: Japanese Ro key. */
    public static final int KEY_RO              = 217;
    /** Key code constant: Japanese kana key. */
    public static final int KEY_KANA            = 218;
    /** Key code constant: Assist key.
     * Launches the global assist activity.  Not delivered to applications. */
    public static final int KEY_ASSIST          = 219;
    
    public static final int ACTION_KEY_DOWN = 0;
    public static final int ACTION_KEY_UP 	= 1;
    
    public static final int KEY_UP         		= -1;
    public static final int KEY_DOWN       		= -1;
    public static final int KEY_LEFT       		= -1;
    public static final int KEY_RIGHT      		= -1;
    
    public int 		mAction 		= -1;
    public int 		mKey 			= -1;
    public char 	mUnicodeChar 	= ' ';
    
    public BitsKeyEvent() {
    	
    }
    
    public final boolean isLetter( ) {
    	if( mKey >= KEY_A && mKey <= KEY_Z ) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public final boolean isNumber( ) {
    	if( mKey >= KEY_0 && mKey <= KEY_9 ) {
    		return true;
    	} else {
    		return false;
    	}    	
    }
    
    public final void reset( ) {
    	this.mAction 		= -1;
    	this.mKey 			= -1;
    	this.mUnicodeChar	= ' ';
    }
}
