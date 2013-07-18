package org.romil.processing;

import java.io.IOException;
import java.util.List;

import cmu.arktweetnlp.Tagger;
import cmu.arktweetnlp.Tagger.TaggedToken;

public class CMUTagger {
	String model;
	Tagger tagger;
	public CMUTagger(){
		model = "/home/romil/Documents/jars/ark-tweet-nlp-0.3.2/model.20120919";
		tagger = new Tagger();
		try {
			tagger.loadModel(model);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<TaggedToken> tagTweet(String tweet){
		try {	
			List<TaggedToken> taggedTokens = tagger.tokenizeAndTag(tweet);
			return taggedTokens;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String tweet = "@atlgc17 Im 16 and a freshmen again. I fluncked once in elementry school and I fluncked last year because I never did my work.";
		String text = "RT @DjBlack_Pearl: wat muhfuckaz wearin 4 the lingerie party?????";
		tweet = "today in a nutshell; abusing blind people, seducing teachers, chinese takeaways, gatt holly, phil collins, the skin of a goblins nose, lush";
		
		CMUTagger ct = new CMUTagger();
		List<TaggedToken> taggedTokens = ct.tagTweet(tweet);

		for (TaggedToken token : taggedTokens) {
			System.out.printf("%s\t%s\n", token.tag, token.token);
		}
	}

}
