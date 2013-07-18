package org.romil.processing;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import cmu.arktweetnlp.Tagger.TaggedToken;

public class JsoupExtractor {
	public static void main(String[] args) throws IOException {
		System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
		System.setProperty("http.proxyPort", "8080");
		Document doc = Jsoup.connect("https://t.co/yCGN8OK9v8").get();
		//get text only
		removeComments(doc); 
		String text = doc.body().text();
		System.out.println(text);
		CMUTagger ct = new CMUTagger();
		List<TaggedToken> taggedTokens =ct.tagTweet(text);
		for (TaggedToken token : taggedTokens) {
			System.out.printf("%s\t%s\n", token.tag, token.token);
		}
	}

	private static void removeComments(Node node) {
		int i = 0;
		while (i < node.childNodes().size()) {
			Node child = node.childNode(i);
			if (child.nodeName().equals("#comment"))
				child.remove();
			else {
				removeComments(child);
				i++;
			}
		} //To change body of generated methods, choose Tools | Templates.
	}

}
