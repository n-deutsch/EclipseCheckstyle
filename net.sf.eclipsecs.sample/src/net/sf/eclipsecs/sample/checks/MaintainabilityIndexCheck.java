package net.sf.eclipsecs.sample.checks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//calculates the maintainability index
public class MaintainabilityIndexCheck extends AbstractCheck {
  
  //MI = 171 - 5.2 * LOG2(v) - 0.23 * G - 16.2 * log2(LOC) + 50 * sin(sqrt(2.4))
  
  HashSet<Integer> foundoperators = new HashSet<Integer>();
  int totaloperators;
  HashSet<Integer> foundoperands = new HashSet<Integer>();
  int totaloperands;
  
  int commentLines = 0;
  int totalLines = 0;
  
  int graphEdges = 0;
  int graphNodes = 0;
  int exitProcess = 1;
  
  int[] operators = {
      TokenTypes.ASSIGN,
      TokenTypes.BAND,
      TokenTypes.BAND_ASSIGN,
      TokenTypes.BNOT,
      TokenTypes.BOR,
      TokenTypes.BOR_ASSIGN,
      TokenTypes.BSR,
      TokenTypes.BSR_ASSIGN,
      TokenTypes.BXOR,
      TokenTypes.BXOR_ASSIGN,
      TokenTypes.COLON,
      TokenTypes.COMMA,
      TokenTypes.DEC,
      TokenTypes.DIV,
      TokenTypes.DIV_ASSIGN,
      TokenTypes.DOT,
      TokenTypes.EQUAL,
      TokenTypes.GE,
      TokenTypes.GT,
      TokenTypes.INC,
      TokenTypes.INDEX_OP,
      TokenTypes.LAND,
      TokenTypes.LE,
      TokenTypes.LITERAL_INSTANCEOF,
      TokenTypes.LNOT,
      TokenTypes.LOR,
      TokenTypes.LT,
      TokenTypes.MINUS,
      TokenTypes.MINUS_ASSIGN,
      TokenTypes.MOD,
      TokenTypes.MOD_ASSIGN,
      TokenTypes.NOT_EQUAL,
      TokenTypes.PLUS,
      TokenTypes.PLUS_ASSIGN,
      TokenTypes.POST_DEC,
      TokenTypes.POST_INC,
      TokenTypes.QUESTION,
      TokenTypes.SL,
      TokenTypes.SL_ASSIGN,
      TokenTypes.SR,
      TokenTypes.SR_ASSIGN,
      TokenTypes.STAR,
      TokenTypes.STAR_ASSIGN,
      TokenTypes.UNARY_MINUS,
      TokenTypes.UNARY_PLUS 
  };
  
  int[] operands = {
      TokenTypes.TYPE,
      TokenTypes.WILDCARD_TYPE,
      TokenTypes.CHAR_LITERAL,
      TokenTypes.STRING_LITERAL,
      TokenTypes.ENUM_CONSTANT_DEF
  };
  
  int[] extraTokens = {
      TokenTypes.SINGLE_LINE_COMMENT,
      TokenTypes.BLOCK_COMMENT_BEGIN,
      TokenTypes.CLASS_DEF,
      TokenTypes.LITERAL_IF
  };
  
  @Override
  public int[] getAcceptableTokens() {
    int offset = operators.length + operands.length;
    int[] allwatches = new int[operators.length + operands.length + extraTokens.length];
    System.arraycopy(operators, 0, allwatches, 0, operators.length);
    System.arraycopy(operands, 0, allwatches, operators.length, operands.length);
    System.arraycopy(extraTokens, 0, allwatches, offset, extraTokens.length);
    return allwatches;
  }

  @Override
  public int[] getRequiredTokens() {
    int offset = operators.length + operands.length;
    int[] allwatches = new int[operators.length + operands.length + extraTokens.length];
    System.arraycopy(operators, 0, allwatches, 0, operators.length);
    System.arraycopy(operands, 0, allwatches, operators.length, operands.length);
    System.arraycopy(extraTokens, 0, allwatches, offset, extraTokens.length);
    return allwatches;
  }

  @Override
  public int[] getDefaultTokens() {
    int offset = operators.length + operands.length;
    int[] allwatches = new int[operators.length + operands.length + extraTokens.length];
    System.arraycopy(operators, 0, allwatches, 0, operators.length);
    System.arraycopy(operands, 0, allwatches, operators.length, operands.length);
    System.arraycopy(extraTokens, 0, allwatches, offset, extraTokens.length);
    return allwatches;
  }
  
  //necessary override so the tree visits comment nodes
  @Override
  public boolean isCommentNodesRequired() {
    return true;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
    int type = ast.getType();
    
    int open = 0;
    int close = 0;
    
    if (type == TokenTypes.SINGLE_LINE_COMMENT) {
      commentLines++;
    } else if (type == TokenTypes.BLOCK_COMMENT_BEGIN) {
      open = ast.getLineNo();
      close = ast.getLastChild().getLineNo();
      commentLines = commentLines + (close - open) + 1;
    } else if (type == TokenTypes.LITERAL_IF) {
      graphNodes = graphNodes + 2;
    } else if (type == TokenTypes.CLASS_DEF) {
      totalLines = ast.getLastChild().getLineNo();
    } else if (IntStream.of(operators).anyMatch(i -> i == type)) {
      totaloperators++;
      foundoperators.add(type);
    } else if (IntStream.of(operands).anyMatch(i -> i == type)) {
      totaloperands++;
      foundoperands.add(type);
    }
  }
  
  //MI = 171 - 5.2 * LOG2(v) - 0.23 * G - 16.2 * log2(LOC) + 50 * sin(sqrt(2.4))
  @Override 
  public void finishTree(DetailAST ast) {
    //calculate maintainability index
    double mi = 0;
    
    //get halstead volume
    double halsteadVolume = (totaloperators + totaloperands)* (Math.log(foundoperators.size()+foundoperands.size())/Math.log(2));
    
    //find cyclomatic complexity
    /*
     * Cyclomatic complexity = E - N + 2*P
     * E = Edges of flow graph
     * N = Number of nodes in flow graph
     * P = Nodes that have an exit process
     * */
    double cyclomaticComplexity = (totalLines - commentLines) - graphNodes + (2 * exitProcess);
    
    //final calculation
    mi = 171 - (5.2 * (Math.log(halsteadVolume / Math.log(2)))) - (0.23 * cyclomaticComplexity) - (16.2 * (Math.log(totalLines) / Math.log(2))) + (50 * Math.sin(Math.sqrt(2.4)));
    
    //display calculated index
    String message = "Maintainability Index: " + mi;
    log(ast.getLineNo(), message);
    
    //reset operands, operators, ect
    commentLines = 0;
    totalLines = 0;
    totaloperators = 0;
    totaloperands = 0;
    graphNodes = 0;
    exitProcess = 0;
    foundoperands = new HashSet<Integer>();
    foundoperators = new HashSet<Integer>();
  }
}
