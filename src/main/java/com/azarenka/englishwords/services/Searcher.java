package com.azarenka.englishwords.services;

import com.azarenka.englishwords.domain.Words;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Searcher {

    @Autowired
    private ServiceProvider serviceProvider;

    public List<Words> search(String word) {
        if(word.isEmpty()){
            return serviceProvider.getWordParser().getAllWords();
        }
        List<String> rW = serviceProvider.getWordParser().getRussianWords();
        List<String> eW = serviceProvider.getWordParser().getEnglishWords();
        Words words = new Words();
        Integer n = searchByRussian(word, rW);
        if (null != n) {
            words.setRu(rW.get(n));
            words.setEn(eW.get(n));
        } else {
            n = searchByEngilsh(word, eW);
            if (null != n) {
                words.setRu(rW.get(n));
                words.setEn(eW.get(n));
            }
        }
        ArrayList<Words> list = new ArrayList<>();
        list.add(words);
        return list;
    }

    private Integer searchByEngilsh(String word, List<String> eW) {
        for (int i = 0; i < eW.size(); i++) {
            if(eW.get(i).contains(word)){
                return i;
            }
        }
        return null;
    }

    private Integer searchByRussian(String word, List<String> rW) {
        for (int i = 0; i < rW.size(); i++) {
            if(rW.get(i).contains(word)){
                return i;
            }
        }
        return null;
    }
}
