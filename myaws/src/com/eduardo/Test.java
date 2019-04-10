/**
 * 
 */
package com.eduardo;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.AmazonServiceException;

public class Test{


    /**
     * 上传文件到指定的bucket中。
     * 注意点：这里的本地路径的文件必须存在才行，不然文件不存在回异常。
     */
    public static void main(String[] args) {
    	
    	//存储桶的名字
        String bucket_name="testkyle1";
        //自定义上传到bucket后的目录结构
        String key_name="haidingqu/xierqi/aws-sysops.pdf";
        //本地文件路径
        String file_path="C:/Users/patpat/Downloads/aws-sysops.pdf";
        //This is test
    	
       // @SuppressWarnings("deprecation")
		final AmazonS3 s3 = new AmazonS3Client();
        try {
            s3.putObject(bucket_name, key_name, file_path);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }
}