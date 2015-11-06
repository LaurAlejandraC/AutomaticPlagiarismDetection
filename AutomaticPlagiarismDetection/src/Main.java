import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Java8Lexer lexer = new Java8Lexer(new ANTLRFileStream("Test.java"));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Java8Parser parser = new Java8Parser(tokens);
	}

}
