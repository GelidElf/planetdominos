package app.gpwars.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BotTranslator {

	private enum StateType {q0, q1, q2, q3};

	/**
	 * @param args
	 */
	public static String translate(String sExpression) {
		String javaCode = "";
		StringBuffer buff = null;
		List<String> buffTokens;
		Stack<String> stack = new Stack<String>();
		String block;
		int index = 0;
		char c = sExpression.charAt(index++);

		//Initialize the finite Automata:
		StateType state = StateType.q0;
		while (!state.equals(StateType.q3)) {
			switch(state) {
			case q0:
				switch (c) {
				case '\t':
				case ' ' :
				case '\n':
				case '\r': 
					//Read another char and stay in initial state:
					if (index < sExpression.length()) {
						c = sExpression.charAt(index++);
					} else {
						javaCode = stack.pop();
						state = StateType.q3;
					}
					break;
				case '(':
					//Push the parenthesis in the stack, read another char, and stay in initial state
					stack.push(String.valueOf(c));
					if (index < sExpression.length()) {
						c = sExpression.charAt(index++);
					} else {
						javaCode = "ERROR: Malformed s-expression";
						state = StateType.q3;
					}
					break;
				case ')':
					//If we find a ')' then we must enter state q2
					state = StateType.q2;
					break;
				default:
					// When we find any character that is not a space, a tab, or a parenthesis, we must enter into state q1.
					buff = new StringBuffer();
					state = StateType.q1;
					break;
				}
				break;
			case q1:
				/* In this state we accumulate the current char in a buff. Then we extract another character from the sExpression:
				 * 		- If the character is a letter or a number, then we stay in this state.
				 * 		- If the character is ' ', '\t', or '(' then we push the contents of the buff into the stack and go to state q0
				 * 		- If the character is a ')', then we push the contents of the buff into the stack and go to state q2
				 */
				buff.append(c);
				if (index < sExpression.length()) {
					c = sExpression.charAt(index++);
					switch (c) {
					case '\t':
					case ' ' :
					case '\n':
					case '\r':
					case '(':
						block = buff.toString();
						stack.push(block);
						state = StateType.q0;
						break;
					case ')':
						block = buff.toString();
						stack.push(block);
						state = StateType.q2;
						break;
					default:
						//Stay in state q1
						break;
					}
				} else {
					javaCode = "ERROR: Malformed s-expression";
					state = StateType.q3;
				}
				break;

			case q2:
				/* Int his state, we pop elements from the stack until a '(' is popped, then we form the java expression
				 * with the popped elements. Finally, if we reached the end of the sExpression, then go to state q3, otherwise
				 * read another char and go to state q0
				 */
				buffTokens = new ArrayList<String>();
				String s = stack.pop();
				while (!s.equals("(")) {
					buffTokens.add(s);
					s = stack.pop();
				}
				/* Now, if buffTokens.size() is 4, then this operation must be an issueOrder. The operator should
				 * be the fourth token, the source planet is the third one, the destination planet is the second,
				 * and the number of ships is the first token (sExpressions are in prefix notation and stacks are
				 * LIFO).
				 * If buffTokens.size() is 3, then this operator must be a concatenetor. The operator is coded in
				 * the third token, the first argument in the second token, and the second argument in the first
				 * token.
				 * If buffTokens.size() is 2, then the operator is unary, and must be a Planet selector
				 * (StrongestPkanet, WeakestPlanet, or similar), so the first token is the argument, and the
				 * second token is the operator
				 *
				 */
				if (buffTokens.get(buffTokens.size()-1).contains("prog2")){
					buff = new StringBuffer();
					buff.append(processTokenIfSingle(buffTokens,1) + "\n");
					buff.append(processTokenIfSingle(buffTokens,0) + "\n");
					block = buff.toString();
					stack.push(block);
				}else 
				//if sentence
				if (buffTokens.get(buffTokens.size()-1).contains("ifthenelse")){
					buff = new StringBuffer();
					buff.append("if ( ");
					buff.append(processTokenIfSingle(buffTokens,2) + " ) {\n");
					buff.append(processTokenIfSingle(buffTokens,1) + "} \n else {");
					buff.append(processTokenIfSingle(buffTokens,0) + "} ");
					block = buff.toString();
					stack.push(block);
				}else if (buffTokens.size() == 4) {
					//With 4 tokens, this operator must be an IssueOrder
					buff = new StringBuffer();
					buff.append(buffTokens.get(3) + " ( ");
					buff.append(processTokenIfSingle(buffTokens,2) + ", ");
					buff.append(processTokenIfSingle(buffTokens,1) + ", ");
					buff.append(processTokenIfSingle(buffTokens,0)+ " ) ");
					block = processOrder(buff.toString());
					stack.push(block);
				} else if (buffTokens.size() == 3) {
					buff = new StringBuffer();
					buff.append(buffTokens.get(2) + " (");
					buff.append(processTokenIfSingle(buffTokens,1) + ", ");
					buff.append(processTokenIfSingle(buffTokens,0) + " )");
					block = processOrder(buff.toString());
					stack.push(block);
				} else if (buffTokens.size() == 2) {
					//With two tokens, this operator must be Strongest, Weakest, FastestsGrowing, or similar:
					buff = new StringBuffer();
					buff.append(buffTokens.get(1) + " ( ");
					buff.append(processTokenIfSingle(buffTokens,0) + " )");
					block = processOrder(buff.toString());
					stack.push(block);
				} else {
					System.out.println("ERROR in \"case ')':\"");
					System.exit(-1);
				}

				if (index < sExpression.length()) {
					c = sExpression.charAt(index);
					state = StateType.q0;
				} else {
					javaCode = stack.pop();
					state = StateType.q3;
				}
				break;

			}

		}

		if (javaCode.startsWith("ERROR")) {
			return "ERROR";
		} else {
			return javaCode;
		}

	}

	private static String processOrder(String string) {
		String[] tokens = string.trim().split(" \\)\\(");
		if (tokens[0].toLowerCase().contains("order") || tokens[0].toLowerCase().contains("overwhelm")){
			return "botOrders.addAll(" + string + ");\n";
		}
		return string;
	}

	public static String processTokenIfSingle(List<String> buffTokens,  int pos){
		String procesedString = buffTokens.get(pos);
		if (!procesedString.trim().contains(")")){
			if (!procesedString.trim().equals("false") && !procesedString.trim().equals("true"))
				if (procesedString.trim().equals("noOrder"))
					return procesedString +" (); ";
				else
					return procesedString +" () ";
		}
		return procesedString;
	}

}
