package org.romil.processing;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

public class BoilerpipeExtractor {
	public String getText(URL url){
		System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
		System.setProperty("http.proxyPort", "8080");
		//URL url = new URL("http://research.microsoft.com/en-us/um/people/ryenw/hcir2010/challenge.html");
		URLConnection conn;
		try {
			conn = url.openConnection();
			final String encoding = conn.getContentEncoding();
			Charset cs = Charset.forName("Cp1252");
			if (encoding != null) {
				try {
					cs = Charset.forName(encoding);
				} catch (UnsupportedCharsetException e) {
					// keep default
				}
			}
			String article = ArticleExtractor.INSTANCE.getText(url);
			return article;
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (BoilerpipeProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) throws IOException, BoilerpipeProcessingException {
		// TODO Auto-generated method stub
		System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
		System.setProperty("http.proxyPort", "8080");
		//URL url = new URL("http://research.microsoft.com/en-us/um/people/ryenw/hcir2010/challenge.html");
		URL url = new URL("http://www.volkswagen.co.in/en.html");
		final URLConnection conn = url.openConnection();

		final String encoding = conn.getContentEncoding();

		Charset cs = Charset.forName("Cp1252");
		if (encoding != null) {
			try {
				cs = Charset.forName(encoding);
			} catch (UnsupportedCharsetException e) {
				// keep default
			}
		}
		String article = ArticleExtractor.INSTANCE.getText(url);
		System.out.println(article);
	}

}
