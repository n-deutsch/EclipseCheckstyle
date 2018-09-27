/*package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts the total number of expressions
public class CommentLineCheck extends AbstractCheck {

  private int commentLineCount = 0;

  @Override
  public int[] getAcceptableTokens() {
    return new int[] { 
        TokenTypes.SINGLE_LINE_COMMENT, 
        TokenTypes.BLOCK_COMMENT_END, 
        TokenTypes.BLOCK_COMMENT_BEGIN };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { 
        TokenTypes.SINGLE_LINE_COMMENT, 
        TokenTypes.BLOCK_COMMENT_END, 
        TokenTypes.BLOCK_COMMENT_BEGIN };
  }

  @Override
  public void visitToken(DetailAST ast) {
    int open = 0;
    int close = 0;
    int distance = 0;
    
    if(ast.getType()==TokenTypes.SINGLE_LINE_COMMENT){
      commentLineCount++;
    }else if(ast.getType()==TokenTypes.BLOCK_COMMENT_BEGIN){
      open = ast.getLineNo();
      close = ast.getLastChild().getLineNo();
      distance = (close - open) + 1;
      commentLineCount = commentLineCount + distance;
    }
    //else if(ast.getType()==TokenTypes.BLOCK_COMMENT_END){}
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    log(ast.getLineNo(), "Number of comment lines: " + this.commentLineCount);
    commentLineCount = 0;
  }
}
*/