package com.highrock.utils;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.util.List;

;

/**
 * Created by user on 2017/9/25.
 */
public class S3Helper {
    private static String BUCKET_STAGE = "72craft-cart-thumbs-stage";
    public static List<S3ObjectSummary> getList(String bucket_name){
        AmazonS3 s3 = S3Helper.getS3Clinet();
        ObjectListing ol = s3.listObjects(bucket_name);

       List<S3ObjectSummary> objects = ol.getObjectSummaries();
       for (S3ObjectSummary os: objects) {
            System.out.println("* " + os.getKey());
        }
        return objects;
    }
    public static void createBucket(String bucketName){
        AmazonS3 s3 = S3Helper.getS3Clinet();
        s3.createBucket(bucketName);
    }
    public static void deleteBucket(String bucketName){
        AmazonS3 s3 = S3Helper.getS3Clinet();
        s3.deleteBucket(bucketName);
    }
    public static boolean upload(String bucketName,String s3Path,String filePath){
        try {
            AmazonS3 s3 = S3Helper.getS3Clinet();
            PutObjectRequest p = new PutObjectRequest(bucketName, s3Path, new File(filePath));
            s3.putObject(p);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    public static boolean delete(String bucketName,String key){
        try {
            AmazonS3 s3 = S3Helper.getS3Clinet();
            DeleteObjectRequest d = new DeleteObjectRequest(bucketName, key);
            s3.deleteObject(d);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    public static boolean delete(String bucketName,String[] keys){
        try {
            AmazonS3 s3 = S3Helper.getS3Clinet();
            DeleteObjectsRequest dor = new DeleteObjectsRequest(bucketName)
                    .withKeys(keys);
            s3.deleteObjects(dor);
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void policy(String bucketName){
        AmazonS3 s3 = S3Helper.getS3Clinet();
        BucketPolicy bucket_policy2 = s3.getBucketPolicy(bucketName);
        String policy_text = "{\"Version\":\"2012-10-17\",\"Id\":\"Policy1505426319490\",\"Statement\":[{\"Sid\":\"Stmt1505426302237\",\"Effect\":\"Allow\",\"Principal\":\"*\",\"Action\":\"s3:GetObject\",\"Resource\":\"arn:aws:s3:::"+bucketName+"/*\"}]}";
        s3.setBucketPolicy(bucketName, policy_text);
        System.out.println(s3.getBucketPolicy(bucketName).getPolicyText());
    }
    private static AmazonS3 getS3Clinet(){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJYNKHDFZCLT3FSFQ", "SOKrT3wwHvmiT9poFD9FY6C0wBl8EKpy3fUQd+kf");
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.US_EAST_2)
                .build();
        return s3;
    }
    public static void main(String args[]){
        S3Helper.policy("highrock-api-log-stage");
    }
}
