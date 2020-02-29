package CSU.Mental.Utils;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang.StringEscapeUtils;

import net.sourceforge.jeval.Evaluator;

import java.util.Calendar;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;

public class CommonUtils {
	
	public String GetNowDate() {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String NowTime = dateFormat.format(now);
		return NowTime;
	}
	
	public String getBeforeMinute(int x) {
		String time = "";
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.add(Calendar.MINUTE, -x);
		time = fd.format(calendar.getTime());
		return time;
	}
	
	public String GetAPITime() {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");//可以方便地修改日期格式
		String NowTime = dateFormat.format(now);
		return NowTime;
	}
	
	public double calculate(String s, double as) {
		try {
			String str = s.replace("AS", "#{a}");
			Evaluator eva = new Evaluator();
			eva.putVariable("a", "" + as);
			return Double.parseDouble(eva.evaluate(str));
		} catch (Exception e) {
			System.out.println(" '''   ");
		}
		return -1;
	}

	public boolean IsNumeric(String str){
		   for(int i=str.length();--i>=0;){
		      int chr=str.charAt(i);
		      //System.out.println(chr);
		      if(chr<48 || chr>57) {
		        if(chr != 46)
		    	  return false;
		      }
		   }
		   return true;
		}

	
	public boolean IsNumber(String num) {
		boolean flag = true;
		
		if(num == null) {
			return false;
		}
		
		char[] str = num.toCharArray();
		
		if(str[0] == '-') {
			for(int i = 1; i < str.length; i ++) {
				if(!(str[i] >= '0' && str[i] <= '9')) {
					flag = false;
					break;
				}
			}
		}
		else {
			for(char s : str) {
				if(!(s >= '0' && s <= '9')) {
					flag = false;
					break;
				}
			}
		}

		return flag;
	}
	
	public static String htmlReplace(String str){
		str = StringEscapeUtils.escapeHtml(str);
        return str;
    }
	
	public String getUrlValue(String urlName) {
        String url = null;
        Properties prop = new Properties();
        try {
            ClassLoader classLoader = CommonUtils.class.getClassLoader();// 读取属性文件xxxxx.properties
            InputStream in = classLoader.getResourceAsStream("config.properties");
            prop.load(in); /// 加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                if (it.next().equals(urlName)) {
                    url = prop.getProperty(urlName);
                }
            }
            in.close();
        } catch (Exception e) {
            
        }
        return url;
    }
	
    public static String getRandomChar(int length) {  
        char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',   
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',   
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };  
        Random random = new Random();  
        StringBuffer buffer = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            buffer.append(chr[random.nextInt(62)]);  
        }  
        return buffer.toString();  
    }
    
    public static String getCode(int length) {  
        char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};  
        Random random = new Random();  
        StringBuffer buffer = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            buffer.append(chr[random.nextInt(10)]);  
        }  
        return buffer.toString();  
    }
    
    public String md5(String inStr) {
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
	
}
