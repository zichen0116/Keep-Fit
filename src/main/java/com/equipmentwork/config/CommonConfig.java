package com.equipmentwork.config;

import com.equipmentwork.repository.RedisChatMemoryStore;
import dev.langchain4j.community.store.embedding.redis.RedisEmbeddingStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class CommonConfig {

    @Autowired
    private RedisChatMemoryStore redisChatMemoryStore;
    @Autowired
    private EmbeddingModel embeddingModel;
    @Autowired
    private RedisEmbeddingStore redisEmbeddingStore;
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        ChatMemoryProvider chatMemoryProvider = new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId){
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .chatMemoryStore(redisChatMemoryStore)
                        .maxMessages(20)
                        .build();
            }

         };
        return chatMemoryProvider;
    }
    // 构建向量数据库操作对象
    @Bean
    public EmbeddingStore store(){
        // 加载文档
        // ---List<Document> documents = ClassPathDocumentLoader.loadDocuments("content",new ApachePdfBoxDocumentParser());
        // 创建向量数据库操作对象  内存版向量数据库
        // InMemoryEmbeddingStore store = new InMemoryEmbeddingStore();


        // 构建文档分割器
        // ---DocumentSplitter ds = DocumentSplitters.recursive(500, 100);
        // 创建一个EmbeddingStoreIngestor对象,用于文本的分割 向量化
        // ---EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                // .embeddingStore(store)
                // .embeddingStore(redisEmbeddingStore)  // redis 版向量数据库
                // .documentSplitter(ds)
                // .embeddingModel(embeddingModel)
                // .build();
        // ---ingestor.ingest(documents);
        return redisEmbeddingStore;
    }

    // 创建向量数据库检索对象
    @Bean
    public ContentRetriever contentRetriever(/* EmbeddingStore embeddingStore */) {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(redisEmbeddingStore)
                .embeddingModel(embeddingModel)
                .minScore(0.7)
                .maxResults(3)
                .build();
    }
}
