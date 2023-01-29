package com.flytodata.console.comm.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranFormat {
    public static String tranNvl(String param, String newParam) {

        if (param == null ||
                param.equals("") ||
                param.length() == 0 ||
                param.equals("null")) {

            String reParam = newParam;
            return reParam;
        }
        else {
            return param.trim();
        }
    }


    public static String tranSosi(String str) throws Exception {
        int ByteSize = str.getBytes().length;
        byte convertstr[] = new byte[ByteSize + 2];
        byte currentstr[] = new byte[ByteSize];
        convertstr[0] = 14;
        currentstr = str.getBytes();
        for (int i = 0; i < currentstr.length; i++)
            convertstr[i + 1] = currentstr[i];

        convertstr[ByteSize + 1] = 15;
        return new String(convertstr);
    }

    public static boolean isValidEmail(String email) {
        boolean valid = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            valid = true;
        }
        return valid;
    }


    public static String tranFillZero(long num, int len) {
        StringBuffer sb = new StringBuffer(len);
        int zeroCount = len - ("" + num).length();
        for (int i = 0; i < zeroCount; i++) {
            sb.append(0);
        }
        sb.append(num);
        return sb.toString();
    }

    public static String tranFillZero(String num, int len) {
        StringBuffer sb = new StringBuffer(len);
        int zeroCount = len -  num.length();
        for (int i = 0; i < zeroCount; i++) {
            sb.append(0);
        }
        sb.append(num);
        return sb.toString();
    }

    public static String tranNumberFormat(int num){
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(num);
    }

    public static String tranDateFormat(String dateStr) throws Exception{
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = dtFormat.parse(dateStr);

        String strNewDtFormat = newDtFormat.format(formatDate);

        return strNewDtFormat;
    }

    public static String tranDateFormat(String dateStr, String pattern) throws Exception{
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat newDtFormat = new SimpleDateFormat(pattern);
        Date formatDate = dtFormat.parse(dateStr);

        String strNewDtFormat = newDtFormat.format(formatDate);

        return strNewDtFormat;
    }

    public static String tranDttmFormat(String dateStr, String pattern) throws Exception{
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat newDtFormat = new SimpleDateFormat(pattern);
        Date formatDate = dtFormat.parse(dateStr);

        String strNewDtFormat = newDtFormat.format(formatDate);

        return strNewDtFormat;
    }

    public static String addDate(String strDate, String strKind, int iAdder) {
        GregorianCalendar cal = getGregorianCalendar(strDate); ;
        if (strKind.equalsIgnoreCase("yy")) {
            cal.add(GregorianCalendar.YEAR, iAdder);
        }
        else if (strKind.equalsIgnoreCase("mm")) {
            cal.add(GregorianCalendar.MONTH, iAdder);
        }
        else if (strKind.equalsIgnoreCase("dd")) {
            cal.add(GregorianCalendar.DATE, iAdder);
        }
        return MakeFormatDate(cal, "yyyyMMdd");
    }

    public static GregorianCalendar getGregorianCalendar(String strDate) {

        int yyyy = Integer.parseInt(strDate.substring(0, 4));
        int mm = Integer.parseInt(strDate.substring(4, 6));
        int dd = Integer.parseInt(strDate.substring(6));

        GregorianCalendar calendar = new GregorianCalendar(yyyy, mm - 1, dd, 0, 0, 0);
        return calendar;
    }

    /**
     * 날짜/시간 의 포맷지정에 따라 스트링으로 반화
     * @param cal
     * @param format
     * @return
     */
    public static String MakeFormatDate(Calendar cal,String format) {
        String retStr = "";
        SimpleDateFormat sim = new SimpleDateFormat(format);
        retStr = sim.format(cal.getTime());
        return retStr;
    }

    public static String MakeFormatDate(String format) {
        Calendar cal = GregorianCalendar.getInstance();
        String retStr = "";
        SimpleDateFormat sim = new SimpleDateFormat(format);
        retStr = sim.format(cal.getTime());
        return retStr;
    }

    public static Hashtable userHash = new Hashtable();
    public static String getUserName(String user_id) throws Exception{

        userHash.put("661", "오늘의집");
        userHash.put("23649", "크림");
        userHash.put("355", "번개장터");
        userHash.put("4068", "29cm");
        userHash.put("2631", "크로켓");
        userHash.put("40346", "젠틀몬스터");
        userHash.put("40449", "컴투스");
        userHash.put("3902", "PRND");
        userHash.put("27556", "데이원컴퍼니");

        return (String) userHash.get(user_id);
    }

    public static Hashtable inicisGidHash = new Hashtable();
    public static String getInicisGid(String mid) throws Exception{

        inicisGidHash.put("MOIAplusBH", "HOSTmovin8");
        inicisGidHash.put("MOIAplusBP", "HOSTmovin8");
        inicisGidHash.put("MOIAplusSP", "HOSTmovin8");
        inicisGidHash.put("MOIAplusTS", "HOSTmovin8");
        inicisGidHash.put("aplusb1005", "HOSTmovin8");

        return (String) inicisGidHash.get(mid);
    }

    public static String getSha256(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static String getMD5(String pwd) {
        String MD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            MD5 = null;
        }
        return MD5;
    }



    /**
     * Signature 데이터 생성
     */
    public static String makeSignatureData(PrivateKey priKey, String targetData)
    {
        String signData = null;

        byte[] btArrTargetData = targetData.getBytes( StandardCharsets.UTF_8 );

        try {
            Signature sign = Signature.getInstance( "SHA256WithRSA" );
            sign.initSign( priKey );
            sign.update( btArrTargetData );

            byte[] btArrSignData = sign.sign();

            signData = Base64.getEncoder().encodeToString( btArrSignData );

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return signData;
    }

    public static String getKcpCert(){
        return "-----BEGIN CERTIFICATE-----" +
                "MIIDjDCCAnSgAwIBAgIHBy8ZjDXhMDANBgkqhkiG9w0BAQsFADBzMQswCQYDVQQG" +
                "EwJLUjEOMAwGA1UECAwFU2VvdWwxEDAOBgNVBAcMB0d1cm8tZ3UxFTATBgNVBAoM" +
                "DE5ITktDUCBDb3JwLjETMBEGA1UECwwKSVQgQ2VudGVyLjEWMBQGA1UEAwwNc3Bs" +
                "LmtjcC5jby5rcjAeFw0yMjExMTYwNzUyNDRaFw0yNzExMTUwNzUyNDRaMHsxCzAJ" +
                "BgNVBAYTAktSMQ4wDAYDVQQIDAVTZW91bDEQMA4GA1UEBwwHR3Vyby1ndTEWMBQG" +
                "A1UECgwNTkhOIEtDUCBDb3JwLjEXMBUGA1UECwwOUEdXRUJERVYgVGVhbS4xGTAX" +
                "BgNVBAMMEDIwMjIxMTE2MTAwMDMzMjAwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAw" +
                "ggEKAoIBAQCbwY+315MxizHJyCmhxJqZrzBEsn725krNVUNVGR+gIyMmMr+e7iNx" +
                "qpgZM3r+uwdBK/apNmhXOjgA6ulAP5zTOlrJDiOaDXbtx1DfUP+sAV6YJex36GM1" +
                "BIA0LtrZ8iDX0s0i/z95XpIatPPsjF02pgzLp8G6jBnWLdjZO+2N+5lJdqsEDdDQ" +
                "DFgF91TWe85Fw158kunc7rj6fJOtyk4xQdGi+66lwhf/UQiRJXw8Ki8/DHNGBSii" +
                "FAn2OKMKh1B70HCMK5g3dDiJbz1CDFkEqV/V8nc3u2S17rykOiSgbRmub3Lsq9xX" +
                "hRXl0jxMswH0J3yAUzxmFoQEJ+PeHWcbAgMBAAGjHTAbMA4GA1UdDwEB/wQEAwIH" +
                "gDAJBgNVHRMEAjAAMA0GCSqGSIb3DQEBCwUAA4IBAQCEl7jxoEGZHXNoYZH/oYu7" +
                "ESFgT+g1vVvurowQruC4KCBmkF5xuRKCJ93Nx3KRWy/89lhCgx+RBjP/UM0VjQCW" +
                "Xi4xzRH3I/GuXGT3kdXzBv2SAl1yr/nlmolExn0+vqKD/m9yrv6YCia/R1B2ds4s" +
                "DgqIOSANQvpf4Lc5kP3f3hQ9qp8BGyRrV7BNjAV8/zB9oaGub3iUN/gRfmdT/2hp" +
                "5RL7wTapzj9a/iTfWkc0KSWscxegN63O0SC6H7Ga0hOomCqmwpEFGdIrtrQ3uU7S" +
                "M9gPNz1gwvem2e1IzZIq8unU4mIr8EcFRmx3j/GF2y4I58Sbu7fNbukDmVNKHBRX" +
                "-----END CERTIFICATE-----";
    }


}
