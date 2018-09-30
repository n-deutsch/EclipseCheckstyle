package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts the total number of lines of comments
public class CommentLinesCheck extends AbstractCheck {

  private int commentLinesCount = 0; //default value is zero

  @Override
  public int[] getAcceptableTokens() {
    return new int[] { 
        TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
  }

  //necessary override so our tree doesn't ignore comments
  @Override
  public boolean isCommentNodesRequired() {
    return true;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
    int open = 0; //line no of BLOCK_COMMENT_BEGIN
    int close = 0; //line no of BLOCK_COMMENT_END
    
    if (ast.getType() == TokenTypes.SINGLE_LINE_COMMENT) {
      commentLinesCount++; //increase number of lines
    } else if (ast.getType() == TokenTypes.BLOCK_COMMENT_BEGIN) {
      //take the difference between the block comments
      open = ast.getLineNo();
      close = ast.getLastChild().getLineNo();
      //increase the total by 1, this is so block comments on the same line count as 1 instead of 0
      commentLinesCount = commentLinesCount + (close - open) + 1;
    }
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    //display lines of comments to the user
    String message = "Number of lines of comments: " + this.commentLinesCount;
    log(ast.getLineNo(), message);
    commentLinesCount = 0;
  }
}
