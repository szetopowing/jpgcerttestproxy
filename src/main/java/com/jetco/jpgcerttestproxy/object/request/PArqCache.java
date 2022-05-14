package com.jetco.jpgcerttestproxy.object.request;

import org.springframework.cache.annotation.Cacheable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Cacheable()
public class PArqCache<String, PArq> {
	
	private String testCaseResultId;
    private PArq pArq;
    
}
