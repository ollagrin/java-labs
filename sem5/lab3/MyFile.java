class java.lang.String extends java.lang.Object implements  java.io.Serializable,  java.lang.Comparable,  java.lang.CharSequence,  java.lang.constant.Constable,  java.lang.constant.ConstantDesc,  {
	private final byte[] java.lang.String.value
	private final byte java.lang.String.coder
	private int java.lang.String.hash
	private boolean java.lang.String.hashIsZero
	private static final long java.lang.String.serialVersionUID
	static final boolean java.lang.String.COMPACT_STRINGS
	private static final java.io.ObjectStreamField[] java.lang.String.serialPersistentFields
	private static final char java.lang.String.REPL
	public static final java.util.Comparator java.lang.String.CASE_INSENSITIVE_ORDER
	static final byte java.lang.String.LATIN1
	static final byte java.lang.String.UTF16

	public java.lang.String(java.lang.StringBuffer)
	public java.lang.String(java.lang.StringBuilder)
	java.lang.String(char[],int,int,java.lang.Void)
	java.lang.String(java.lang.AbstractStringBuilder,java.lang.Void)
	public java.lang.String(byte[],int,int,java.nio.charset.Charset)
	public java.lang.String(byte[],java.lang.String) throws java.io.UnsupportedEncodingException
	public java.lang.String(byte[],java.nio.charset.Charset)
	public java.lang.String(byte[],int,int)
	public java.lang.String(byte[])
	java.lang.String(byte[],byte)
	public java.lang.String(char[],int,int)
	public java.lang.String(char[])
	public java.lang.String(java.lang.String)
	public java.lang.String()
	public java.lang.String(byte[],int,int,java.lang.String) throws java.io.UnsupportedEncodingException
	public java.lang.String(byte[],int)
	public java.lang.String(byte[],int,int,int)
	public java.lang.String(int[],int,int)

	byte[] java.lang.String.value()
	public boolean java.lang.String.equals(java.lang.Object)
	public int java.lang.String.length()
	public java.lang.String java.lang.String.toString()
	public int java.lang.String.hashCode()
	public void java.lang.String.getChars(int,int,char[],int)
	public int java.lang.String.compareTo(java.lang.String)
	public int java.lang.String.compareTo(java.lang.Object)
	public int java.lang.String.indexOf(int)
	public int java.lang.String.indexOf(java.lang.String)
	static int java.lang.String.indexOf(byte[],byte,int,java.lang.String,int)
	public int java.lang.String.indexOf(java.lang.String,int)
	public int java.lang.String.indexOf(int,int)
	static void java.lang.String.checkIndex(int,int)
	public static java.lang.String java.lang.String.valueOf(int)
	public static java.lang.String java.lang.String.valueOf(char[])
	public static java.lang.String java.lang.String.valueOf(java.lang.Object)
	public static java.lang.String java.lang.String.valueOf(boolean)
	public static java.lang.String java.lang.String.valueOf(char[],int,int)
	public static java.lang.String java.lang.String.valueOf(char)
	public static java.lang.String java.lang.String.valueOf(double)
	public static java.lang.String java.lang.String.valueOf(float)
	public static java.lang.String java.lang.String.valueOf(long)
	byte java.lang.String.coder()
	private static java.lang.Void java.lang.String.rangeCheck(char[],int,int)
	static void java.lang.String.checkBoundsOffCount(int,int,int)
	private static java.nio.charset.Charset java.lang.String.lookupCharset(java.lang.String) throws java.io.UnsupportedEncodingException
	private static boolean java.lang.String.isNotContinuation(int)
	private static char java.lang.String.decode2(int,int)
	private static int java.lang.String.decodeUTF8_UTF16(byte[],int,int,byte[],int,boolean)
	private static int java.lang.String.scale(int,float)
	private static int java.lang.String.decodeWithDecoder(java.nio.charset.CharsetDecoder,char[],byte[],int,int)
	private static java.lang.String java.lang.String.newStringNoRepl1(byte[],java.nio.charset.Charset)
	static java.lang.String java.lang.String.newStringUTF8NoRepl(byte[],int,int)
	private static void java.lang.String.throwMalformed(int,int)
	private static void java.lang.String.throwMalformed(byte[])
	private static byte[] java.lang.String.encodeUTF8(byte,byte[],boolean)
	private static byte[] java.lang.String.encode8859_1(byte,byte[])
	private static byte[] java.lang.String.encode8859_1(byte,byte[],boolean)
	private static byte[] java.lang.String.encodeASCII(byte,byte[])
	private static byte[] java.lang.String.encodeWithEncoder(java.nio.charset.Charset,byte,byte[],boolean)
	private static byte[] java.lang.String.safeTrim(byte[],int,boolean)
	private static byte[] java.lang.String.encode(java.nio.charset.Charset,byte,byte[])
	private static byte[] java.lang.String.getBytesNoRepl1(java.lang.String,java.nio.charset.Charset)
	private static boolean java.lang.String.isASCII(byte[])
	private static void java.lang.String.throwUnmappable(int)
	private static void java.lang.String.throwUnmappable(byte[])
	private static boolean java.lang.String.isMalformed3(int,int,int)
	private static int java.lang.String.malformed3(byte[],int)
	private static char java.lang.String.decode3(int,int,int)
	private static boolean java.lang.String.isMalformed3_2(int,int)
	private static int java.lang.String.decode4(int,int,int,int)
	private static boolean java.lang.String.isMalformed4(int,int,int)
	private static int java.lang.String.malformed4(byte[],int)
	private static boolean java.lang.String.isMalformed4_2(int,int)
	private static boolean java.lang.String.isMalformed4_3(int)
	private static byte[] java.lang.String.encodeUTF8_UTF16(byte[],boolean)
	boolean java.lang.String.isLatin1()
	public char java.lang.String.charAt(int)
	public int java.lang.String.codePointAt(int)
	public int java.lang.String.codePointBefore(int)
	public int java.lang.String.codePointCount(int,int)
	public int java.lang.String.offsetByCodePoints(int,int)
	static void java.lang.String.checkBoundsBeginEnd(int,int,int)
	public byte[] java.lang.String.getBytes(java.nio.charset.Charset)
	void java.lang.String.getBytes(byte[],int,byte)
	public byte[] java.lang.String.getBytes(java.lang.String) throws java.io.UnsupportedEncodingException
	public void java.lang.String.getBytes(int,int,byte[],int)
	void java.lang.String.getBytes(byte[],int,int,byte,int)
	public byte[] java.lang.String.getBytes()
	public boolean java.lang.String.contentEquals(java.lang.CharSequence)
	public boolean java.lang.String.contentEquals(java.lang.StringBuffer)
	private boolean java.lang.String.nonSyncContentEquals(java.lang.AbstractStringBuilder)
	public boolean java.lang.String.regionMatches(boolean,int,java.lang.String,int,int)
	public boolean java.lang.String.regionMatches(int,java.lang.String,int,int)
	public boolean java.lang.String.startsWith(java.lang.String,int)
	public boolean java.lang.String.startsWith(java.lang.String)
	public int java.lang.String.lastIndexOf(java.lang.String)
	static int java.lang.String.lastIndexOf(byte[],byte,int,java.lang.String,int)
	public int java.lang.String.lastIndexOf(java.lang.String,int)
	public int java.lang.String.lastIndexOf(int,int)
	public int java.lang.String.lastIndexOf(int)
	public java.lang.String java.lang.String.substring(int,int)
	public java.lang.String java.lang.String.substring(int)
	public boolean java.lang.String.isEmpty()
	public java.lang.String java.lang.String.replace(char,char)
	public java.lang.String java.lang.String.replace(java.lang.CharSequence,java.lang.CharSequence)
	public boolean java.lang.String.matches(java.lang.String)
	public java.lang.String java.lang.String.replaceFirst(java.lang.String,java.lang.String)
	public java.lang.String java.lang.String.replaceAll(java.lang.String,java.lang.String)
	public java.lang.String[] java.lang.String.split(java.lang.String)
	public java.lang.String[] java.lang.String.split(java.lang.String,int)
	public static java.lang.String java.lang.String.join(java.lang.CharSequence,java.lang.CharSequence[])
	static java.lang.String java.lang.String.join(java.lang.String,java.lang.String,java.lang.String,java.lang.String[],int)
	public static java.lang.String java.lang.String.join(java.lang.CharSequence,java.lang.Iterable)
	public java.lang.String java.lang.String.toLowerCase()
	public java.lang.String java.lang.String.toLowerCase(java.util.Locale)
	public java.lang.String java.lang.String.toUpperCase()
	public java.lang.String java.lang.String.toUpperCase(java.util.Locale)
	public java.lang.String java.lang.String.trim()
	public java.lang.String java.lang.String.strip()
	public java.lang.String java.lang.String.stripLeading()
	public java.lang.String java.lang.String.stripTrailing()
	private int java.lang.String.indexOfNonWhitespace()
	public java.util.stream.Stream java.lang.String.lines()
	public java.lang.String java.lang.String.repeat(int)
	private int java.lang.String.lastIndexOfNonWhitespace()
	private static int java.lang.String.outdent(java.util.List)
	public boolean java.lang.String.isBlank()
	public char[] java.lang.String.toCharArray()
	public static java.lang.String java.lang.String.format(java.lang.String,java.lang.Object[])
	public static java.lang.String java.lang.String.format(java.util.Locale,java.lang.String,java.lang.Object[])
	public java.lang.Object java.lang.String.resolveConstantDesc(java.lang.invoke.MethodHandles$Lookup) throws java.lang.ReflectiveOperationException
	public java.lang.String java.lang.String.resolveConstantDesc(java.lang.invoke.MethodHandles$Lookup)
	public java.util.stream.IntStream java.lang.String.codePoints()
	static java.lang.String java.lang.String.newStringNoRepl(byte[],java.nio.charset.Charset) throws java.nio.charset.CharacterCodingException
	static byte[] java.lang.String.getBytesUTF8NoRepl(java.lang.String)
	static byte[] java.lang.String.getBytesNoRepl(java.lang.String,java.nio.charset.Charset) throws java.nio.charset.CharacterCodingException
	static int java.lang.String.decodeASCII(byte[],int,char[],int,int)
	public boolean java.lang.String.equalsIgnoreCase(java.lang.String)
	public int java.lang.String.compareToIgnoreCase(java.lang.String)
	public boolean java.lang.String.endsWith(java.lang.String)
	public java.lang.CharSequence java.lang.String.subSequence(int,int)
	public java.lang.String java.lang.String.concat(java.lang.String)
	public boolean java.lang.String.contains(java.lang.CharSequence)
	public java.lang.String java.lang.String.indent(int)
	public java.lang.String java.lang.String.stripIndent()
	public java.lang.String java.lang.String.translateEscapes()
	public java.util.stream.IntStream java.lang.String.chars()
	public java.lang.Object java.lang.String.transform(java.util.function.Function)
	public java.lang.String java.lang.String.formatted(java.lang.Object[])
	public static java.lang.String java.lang.String.copyValueOf(char[],int,int)
	public static java.lang.String java.lang.String.copyValueOf(char[])
	public native java.lang.String java.lang.String.intern()
	static void java.lang.String.checkOffset(int,int)
	static java.lang.String java.lang.String.valueOfCodePoint(int)
	public java.util.Optional java.lang.String.describeConstable()
	private static java.lang.String java.lang.String.lambda$stripIndent$3(int,java.lang.String)
	private static java.lang.String java.lang.String.lambda$indent$2(int,java.lang.String)
	private static java.lang.String java.lang.String.lambda$indent$1(java.lang.String)
	private static java.lang.String java.lang.String.lambda$indent$0(java.lang.String,java.lang.String)
}