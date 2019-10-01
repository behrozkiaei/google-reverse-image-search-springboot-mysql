package com.image.api.service;

import com.image.api.entity.Image;
import com.image.api.entity.Links;
import com.image.api.entity.SearchContent;
import com.image.api.model.FetchPage;
import com.image.api.repository.ContentRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ImageService {
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

    @Autowired
    ContentRepository contentRepository;
    public Set<Links> getImageByUrl(String urlTemp,Image image) throws IOException {
       String url = "http://cactoos.ir/download_ir_files/"+urlTemp;
        Document res = Jsoup.connect("https://images.google.com/searchbyimage?image_url="+url+"&encoded_image=&image_content=&filename=&hl=en&num=50&lr=lang_en").userAgent(USER_AGENT).get();
        if(res.toString().contains("Pages that include matching images")){
            image.setImageIncludedGoogle(true);
        }else{
            image.setImageIncludedGoogle(false);
        }
        Elements eventInfo = res.getElementsByClass("rc");
        contentRepository.save(new SearchContent(url ,eventInfo.toString(),"google" ));
       Set<Links> itemsSet = new HashSet<>();

        for (Element e : eventInfo.select("a")) {

            String  href = e.attr("href");


            String[] referal = href.split("imgrefurl=");

            if(referal.length > 1){
                itemsSet.add( new Links(referal[1].split("&")[0]+"",image,e.text()));
            }


        }

        return  itemsSet;

    }
    //    getImageByUrlForYandex
    public Set<Links> getImageByUrlForYandex(String urlTemp,Image image) throws IOException {
        String url = "http://cactoos.ir/download_ir_files/"+urlTemp;
        Document res = Jsoup.connect("https://yandex.com/images/search?source=collections&&url="+url+"&rpt=imageview").userAgent(USER_AGENT).get();
            if(res.toString().contains("The same picture was not found")){
             image.setImageIncludedYandex(false);
            }else if(res.toString().contains("Sites where the image is displayed")) {
                image.setImageIncludedYandex(true);
            }else{
                image.setImageIncludedYandex(false);
            }
        contentRepository.save(new SearchContent(url ,res.toString(),"page-layout__content-wrapper" ));
        Set<Links> itemsSet = new HashSet<>();

        for (Element e : res.select("a")) {
            String  href = e.attr("href");


//        String[] referal = href.split("imgrefurl=");

            if(href.contains("http://yandex.com/clck")){
                itemsSet.add(new Links(href,image,e.text()));
            }




        }
        return  itemsSet ;

    }
}
