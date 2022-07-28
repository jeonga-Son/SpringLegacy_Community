package com.ll.exam.article;
import com.ll.exam.article.dto.ArticleDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
    //f5키 눌렀을 때 쌓이는게아 니라 모든게 다시 새로 다 만들어지기 때문에 static 넣어야됨.
    private static List<ArticleDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;

        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "제목%d".formatted(id);
            String body = "내용%d".formatted(id);
            write(title, body);
        });
    }

    public static long write(String title, String body) {
        //나중에 사이트 데이터가 21억개가 넘을 것을 대비해서 long으로 잡음.
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id, title, body);
        datum.add(newArticleDto);
        return id;
    }
    public static List<ArticleDto> findAll() {
        return datum;
    }

    public static ArticleDto findById(long id) {
        for ( ArticleDto articleDto : datum ) {
            if ( articleDto.getId() == id ) {
                return articleDto;
            }
        }

        return null;
    }

    public void delete(long id) {
        ArticleDto articleDto = findById(id);

        if (articleDto == null) return;

        datum.remove(articleDto);
    }

    public void modify(long id, String title, String body) {
        ArticleDto articleDto = findById(id);

        if(articleDto == null) return;

        articleDto.setTitle(title);
        articleDto.setBody(body);
    }
}