package com.cary.stq.utils;

import java.util.*;
import java.io.File;

/**
 * The Utility Class to manipulate String.
 *
 * <p>
 * This class provides the basic utility methods to manipulate <code>String</code>.
 */
public class StringUtils {
    private static Vector nonKeyWords = new Vector( 4 );

    static {
        nonKeyWords.addElement( "the" );
        nonKeyWords.addElement( "a" );
        nonKeyWords.addElement( "an" );
        nonKeyWords.addElement( "of" );
    }

    private StringUtils() {
    }

    /**
     * Capitalizes a string, i.e. changing its first letter to uppercase.
     *
     * @param str The String that needs to be capitalized.
     * @return The capitalized string.
     */
    public static String capitalize ( String str ) {
        if ( str == null || str.length() == 0 )
            return str;
        else
            return str.substring( 0, 1 ).toUpperCase() + str.substring( 1 );
    }

    /**
     * Fully capitalizes all words in a String, i.e. changing the first letter of each
     * and every word (except for non key words) in the string to uppercase.
     *
     * @param str The string to be fully capitalized.
     * @return The fully capitalized string.
     */
    public static String capitalizeAllWords ( String str ) {
        if ( str == null || str.length() == 0 )
            return str;

        StringTokenizer tokens = new StringTokenizer( str, " " );
        StringBuffer sb = new StringBuffer();
        String word;

        while ( tokens.hasMoreTokens() ) {
            word = tokens.nextToken();
            sb.append( ' ' );

            if ( !nonKeyWords.contains( word ) ) {
                sb.append( word.substring( 0, 1 ).toUpperCase() );
                sb.append( word.substring( 1 ) );
            } else
                sb.append( word );
        }

        return sb.toString().substring( 1 );
    }

    /**
     * Combines the strings values in the string array into one single string,
     * delimited by the specified delimiter. An emtpy String is returned if
     * the given values array is of size 0.
     *
     * @param values The strings to be combined.
     * @param delimiter The delimiter used to separate the different strings.
     * @return The resultant string combined from the string array separated by
     *         the specified delimiter. Return an emtpy String if the given
     *         values array is of size 0.
     * @throws NullPointerException if the values argument is <code>null</code>
     */
    public static String combine ( String[] values, String delimiter ) {

        if (values == null) {
            throw new NullPointerException("values array is null");
        }

        if (values.length == 0) {
            return "";
        }

        StringBuffer result = new StringBuffer();

        for ( int i = 1; i < values.length; i++ ) {
            result.append( delimiter );
            result.append( values[i] );
        }

        result.insert( 0, values[0] );

        return result.toString();
    }

    /**
     * Removes redundant spaces (the second consecutive space onwards) from a String.
     *
     * @param str The String that needs to be compacted.
     * @return The String which has been compacted.
     */
    public static String compact ( String str ) {
        if ( str == null || str.length() == 0 )
            return str;

        int len = str.length();
        char buf[] = new char[len];
        StringBuffer sb = new StringBuffer();
        str.getChars( 0, len, buf, 0 );
        int i = 0;

        while ( i < len ) {
            if ( buf[i] != ' ' ) /* Found the first space */
                sb.append( buf[i++] );
            else {
                sb.append( ' ' );
                while ( i < len && buf[i] == ' ' ) /* Skip the rest of the spaces */
                    i++;
            }
        }

        return sb.toString();
    }

    /**
     * If a string is null, return it as "".
     *
     * @param str The String that needs to be checked for null value.
     * @return The String that is converted to appropriate string value.
     */
    public static String deNull ( String str ) {
        if ( str == null )
            return "";
        return str;
    }

    /**
     * To return a string which is filled with a specified string.
     * e.g. duplicate("*", 5) returns "*****", duplicate("OK", 3) returns "OKOKOK"
     * repeated for given number of times
     *
     * @param str String to be repeated/duplicated
     * @param times Number of time the string to be repeated/duplicated
     * @return The resulted string with <code>str</code> repeated the specified number of times.
     */
    public static String duplicate ( String str, int times ) {
        StringBuffer result = new StringBuffer();

        for ( int i = 0; i < times; i++ ) {
            result.append( str );
        }
        return ( result.toString() );
    }

    /**
     * Get the count of occurrences of the character in the target string.
     *
     * @param str The String used to check for the character occurrenct count.
     * @param ch The character to be counted in the string.
     * @return Number of occurrences of the character in the target string.
     */
    public static int getCount ( String str, char ch ) {
        int pos;
        int count = 0;

        do {
            pos = str.indexOf( ch );

            if ( pos != -1 ) {
                count++;

                if ( pos != str.length() )
                    str = str.substring( pos + 1, str.length() );
                else
                    pos = -1;
            }

        } while ( pos != -1 );

        return count;
    }

    /**
     * Checks if the length of the string is of the length specified.
     *
     * @param str The string to test for the length.
     * @param len The length that the string should conform to.
     * @return A boolean value that indicates if the string is of the length specified.
     */
    public static boolean isLengthEqual ( String str, int len ) {
        if ( str == null ) {
            return false;
        } // if (str == null)

        return ( str.length() == len ) ? true : false;
    }

    /**
     * Tests whether the specified string's length is less than or equal to the
     * specified length.
     *
     * @param str The string to test for the length.
     * @param len The length that the string should conform to.
     * @return A boolean value that indicates if the string is at most the length specified.
     */
    public static boolean isLengthLessThan ( String str, int len ) {
        if ( str == null ) {
            return false;
        } // if (str == null)

        return ( str.length() <= len ) ? true : false;
    }

    /**
     * To pad the given string with a user specified character on the left up to the
     * given length.
     * e.g. lPad("ABCD", 10, 'X') returns "XXXXXXABCD" which has a length of 10.
     * This method has built-in 'intelligence' to handle cases where calling method
     * If <I>str</I> already longer than <I>length</I>, return <I>str</I> itself.
     * tries to be funny and supply the following :
     * - lPad("abc", 10, "123") it will return, "1231231abc"
     *
     * @param str String to be padded
     * @param length he required length of the resulted string.
     * @param padString The required padding string
     * @return The padded string
     */
    public static String lPad ( String str, int length, String padString ) {
        int lOriginal = str.length();
        int lPadStr = padString.length();
        int times2Pad = 0;
        int lPadded = 0;

        if ( lOriginal >= length )
            return str;

        StringBuffer sb = new StringBuffer();
        String padded;

        times2Pad = ( length - lOriginal ) / lPadStr;  //will give (1) if 3/2

        padded = duplicate( padString, times2Pad );
        lPadded = padded.length();
        sb.append( padded );        //pad in the repetitive characters

        //if still insufficient by the modulus e.g. 30/20 is 10
        if ( lOriginal + lPadded < length ) {
            int more = length - ( lOriginal + lPadded );

            //add in the difference which is less entire length of padStr
            sb.append( padString.substring( 0, more ) );
        }

        sb.append( str ); //pad the original string behind

        return sb.toString();
    }

    /**
     * Pads the string with prevailing spaces.
     *
     * @param str String to be padded with spaces on the left.
     * @param len The number of spaces to pad to the left of the string.
     * @return The space-padded string.
     */
    public static String lPad ( String str, int len ) {
        return lPad( str, len, " " );
    }

    /**
     * Remove all occurrences of the match in the target string.
     *
     * @param str The String to be checked and have the occurrences of the matching String removed.
     * @param match The matching string.
     * @return The resultant string with all matching string removed.
     */
    public static String removeAllMatch ( String str, String match ) {

        if (str == null || match == null || str.length() == 0 || match.length() == 0) {
            return "";
        }

        StringBuffer newStr = new StringBuffer();

        int endpos = 0;
        for ( int startpos = str.indexOf( match, endpos ); startpos != -1; startpos = str.indexOf( match, endpos ) ) {
            newStr.append( str.substring( endpos, startpos ) );
            endpos = startpos + match.length();
        }

        newStr.append( str.substring( endpos ) );

        return newStr.toString();
    }

    /**
     * Replace the occurrence of a key within the existing string with the required
     * value.
     *
     * @param str Existing String to be replace
     * @param key Key within the String to be searched and replaced
     * @param replacement The replaced value
     * @return The resulted string
     */
    public static String replaceAll ( String str, String key, String replacement ) {

        // Split the string with the key as the delimiter
        String[] parts = StringUtils.split(str, key);
        StringBuffer sb = new StringBuffer( parts[0] );

        for ( int i = 1; i < parts.length; i++ ) {
            sb.append( replacement + parts[i] );
        }
        return sb.toString();
    }
    
    /**
     * Fast string replaceAll function
     * @param str Existing String to be replace
     * @param key Key within the String to be searched and replaced
     * @param replacement The replaced value
     * @param initialStringBufferLength
     * @return The resulted string
     */
    public static String replaceAll(String str, String key, String replacement, int initialStringBufferLength)
	{
		int strLen = str.length();
		int oldStrLen = key.length();
		 
		if (str == null || key == null || replacement == null || strLen == 0 || oldStrLen == 0) {
			return str;
		}
		
		//modify the intial length as necessary
		StringBuffer buf = new StringBuffer(initialStringBufferLength);
		char[] charArray = str.toCharArray();
		
		int oldPos = 0;
		int pos = 0;
		while((pos = str.indexOf(key, oldPos)) > -1) {
			buf.append(charArray, oldPos, (pos-oldPos)).append(replacement);
			oldPos = pos+oldStrLen;
		}
		 
		if (oldPos < strLen) {
			buf.append(charArray, oldPos, (strLen-oldPos));
		}
		 
		return buf.toString();
    }
    
    /**
     * Replaces the first substring of this string that matches the given key with the
     * given replacement.
     *
     * @param str The String to be replaced
     * @param key Key within the String to be searched and replaced
     * @param replacement The String used to replace
     * @return The String with the first occurence of the key value replaced.
     */
    public static String replaceFirst ( String str, String key, String replacement ) {
        StringBuffer result = new StringBuffer( str );

        int pos = str.indexOf( key );

        if ( pos >= 0 ) {
            result.replace( pos, pos + key.length(), replacement );
            //System.out.println( "result = " + result );
        }
        return result.toString();
    }

    /**
     * Replaces the last substring of this string that matches the given key with the
     * given replacement.
     *
     * @param str The String to be replaced
     * @param key Key within the String to be searched and replaced
     * @param replacement The String used for replacement
     * @return The String with the last occurence of the key value replaced.
     */
    public static String replaceLast ( String str, String key, String replacement ) {
        StringBuffer result = new StringBuffer( str );

        int pos = str.lastIndexOf( key );

        if ( pos >= 0 ) {
            result.replace( pos, pos + key.length(), replacement );
            System.out.println( "result = " + result );
        }
        return result.toString();
    }

    /**
     * To pad the given string with spaces up to the given length. <br>
     * e.g. rPad("ABCD", 10, ' ') returns "ABCD      " which has a length of 10.
     *
     * This method has built-in 'intelligence' to handle cases where
     * calling method tries to be funny and supply the following<br>
     * - rPad("abc", 10, "123") it will return, "abc1231231"
     *
     * @param str String to be padded
     * @param length The required length of the resulted string
     * @param padString The required padding string.
     * @return The padded string.
     * If str already <I>longer</I> than <I>length</I>, return str itself.
     */
    public static String rPad ( String str, int length, String padString ) {
        int lOriginal = str.length();
        int lPadStr = padString.length();
        int times2Pad = 0;
        int lPadded = 0;

        if ( lOriginal >= length )
            return str;

        StringBuffer sb = new StringBuffer( str ); //add the original str first
        String padded;

        times2Pad = ( length - lOriginal ) / lPadStr;  //will give (1) if 3/2

        padded = duplicate( padString, times2Pad );
        lPadded = padded.length();
        sb.append( padded );        //pad in the repetitive characters

        //if still insufficient by the modulus e.g. 30/20 is 10
        if ( lOriginal + lPadded < length ) {
            int more = length - ( lOriginal + lPadded );

            //add in the difference which is less entire length of padStr
            sb.append( padString.substring( 0, more ) );
        }

        return sb.toString();
    }

    /**
     * Pads the string with following spaces.
     *
     * @param str The String to be padded with spaces on the right.
     * @param len The number of spaces to pad to the right of the string.
     * @return The resultant string with spaces padded on the right.
     */
    public static String rPad ( String str, int len ) {
        return rPad( str, len, " " );
    }

    /**
     * Parse a string and split into various parts by the specified delimiters.
     *
     * @param str the string to be split
     * @param delimiter delimiting character(s)
     * @return The string array containing the parts delimited by the specified delimiter.
     */
    public static String[] split ( String str, String delimiter ) {
        // tentatively allocate only vector of size 3.
        // if not enough, vector will increment 3 each time.
        Vector result = new Vector( 3, 3 );

        int index = 0;
        int pos = 0;
        int count = 0;

        while ( true ) {
            pos = str.indexOf( delimiter, index );

            //no more parts
            if ( pos == -1 ) {
                result.add( count, str.substring( index ) );
                count++;
                break;
            }

            //put into the array
            result.add( count, str.substring( index, pos ) );

            //increment count
            count++;

            // must cater for delimiter with length > 1
            // so cannot just + 1
            index = pos + delimiter.length();

        }  // parseString()

        //compact the array
        String[] tmp = new String[count];
        System.arraycopy( result.toArray(), 0, tmp, 0, count );

        result = null;
        return tmp;
    }

    /**
     * &quot;normalize&quot; the given absolute path.
     *
     * <p>This includes:
     * <ul>
     *   <li>Uppercase the drive letter if there is one.</li>
     *   <li>Remove redundant slashes after the drive spec.</li>
     *   <li>resolve all ./, .\, ../ and ..\ sequences.</li>
     *   <li>DOS style paths that start with a drive letter will have
     *     \ as the separator.</li>
     * </ul>
     *
     * @param path  the absolute path
     * @throws NullPointerException if the file path is
     * equal to null.
     */
    public static String normalize ( String path ) {
        String orig = path;

        path = path.replace( '/', File.separatorChar )
            .replace( '\\', File.separatorChar );

        // make sure we are dealing with an absolute path
        int colon = path.indexOf( ":" );

        if ( !path.startsWith( File.separator )
            && ( colon == -1 ) ) {
            throw new RuntimeException( path + " is not an absolute path" );
        }

        boolean dosWithDrive = false;
        String root = null;
        // Eliminate consecutive slashes after the drive spec
        if ( path.length() >= 2 &&
            Character.isLetter( path.charAt( 0 ) ) &&
            path.charAt( 1 ) == ':' ) {

            dosWithDrive = true;

            char[] ca = path.replace( '/', '\\' ).toCharArray();
            StringBuffer sbRoot = new StringBuffer();
            for ( int i = 0; i < colon; i++ ) {
                sbRoot.append( Character.toUpperCase( ca[i] ) );
            }
            sbRoot.append( ':' );
            if ( colon + 1 < path.length() ) {
                sbRoot.append( File.separatorChar );
            }
            root = sbRoot.toString();

            // Eliminate consecutive slashes after the drive spec
            StringBuffer sbPath = new StringBuffer();
            for ( int i = colon + 1; i < ca.length; i++ ) {
                if ( ( ca[i] != '\\' ) ||
                    ( ca[i] == '\\' && ca[i - 1] != '\\' ) ) {
                    sbPath.append( ca[i] );
                }
            }
            path = sbPath.toString().replace( '\\', File.separatorChar );

        } else {
            if ( path.length() == 1 ) {
                root = File.separator;
                path = "";
            } else if ( path.charAt( 1 ) == File.separatorChar ) {
                // UNC drive
                root = File.separator + File.separator;
                path = path.substring( 2 );
            } else {
                root = File.separator;
                path = path.substring( 1 );
            }
        }

        Stack s = new Stack();
        s.push( root );
        StringTokenizer tok = new StringTokenizer( path, File.separator );
        while ( tok.hasMoreTokens() ) {
            String thisToken = tok.nextToken();
            if ( ".".equals( thisToken ) ) {
                continue;
            } else if ( "..".equals( thisToken ) ) {
                if ( s.size() < 2 ) {
                    throw new RuntimeException( "Cannot resolve path " + orig );
                } else {
                    s.pop();
                }
            } else { // plain component
                s.push( thisToken );
            }
        }

        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i < s.size(); i++ ) {
            if ( i > 1 ) {
                // not before the filesystem root and not after it, since root
                // already contains one
                sb.append( File.separatorChar );
            }
            sb.append( s.elementAt( i ) );
        }


        path = sb.toString();
        if ( dosWithDrive ) {
            path = path.replace( '/', '\\' );
        }
        return path;

    }

    /**
     * Remove the leading and trailing whitespaces from the given string.
     * If the string is <code>null</code>, return an empty String.
     *
     * @param str the String that needs to be trimmed.
     * @return the trimmed String;
     *         empty String if the given String is <code>null</code>
     */
    public static String trim ( String str ) {
        return deNull(str).trim();
    }

    /**
     * Determine if the input is an "english" character String;
     * @param str the character String
     * @return <code>true</code> if it is an "english" character String
     *         <code>false</code> otherwise
     */
    public static boolean isEnglish( String str ) {

        str = deNull(str);
        for (int i=0; i<str.length(); i++) {
            if (! Character.UnicodeBlock.of(str.charAt(i)).equals(Character.UnicodeBlock.BASIC_LATIN)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Determine if the input is empty;
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if(str == null || str.trim().length() == 0)
            return true;
        return false;
    }

    public static boolean isNotEmpty(String str) {
        if(str == null || str.trim().length() == 0)
            return false;
        return true;
    }

}
