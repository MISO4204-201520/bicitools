/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.uploadfiles;

import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;



import org.apache.http.util.EntityUtils;

/**
 *
 * @author Taidy
 */
public class PostFile {
 /*   public void enviar (){
    try{
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

    HttpPost httppost = new HttpPost("http://localhost/bicitools_login_red_rocial/upload.php");
    File file = new File("D:/butterfly.jpg");

    MultipartEntity mpEntity = new MultipartEntity();
    ContentBody cbFile = new FileBody(file, "image/jpeg");
    mpEntity.addPart("userfile", cbFile);


    httppost.setEntity(mpEntity);
    System.out.println("executing request " + httppost.getRequestLine());
    HttpResponse response = httpclient.execute(httppost);
    System.out.println("executing request 2" );
    HttpEntity resEntity = response.getEntity();
System.out.println("executing request 3" );
    System.out.println(response.getStatusLine());
    System.out.println("executing request 3" );
    if (resEntity != null) {
        System.out.println("executing request 4" );
      System.out.println(EntityUtils.toString(resEntity));
    }
    System.out.println("executing request 5" );
    if (resEntity != null) {
        System.out.println("executing request 6" );
      resEntity.consumeContent();
    }
System.out.println("executing request 7" );
    httpclient.getConnectionManager().shutdown();
    
    }catch(Exception e){
    
    System.out.println("executing request 8:"+e.toString() );
    
    }
  }
    */

}
