package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts the total number of variable declarations
public class CommentsCheck extends AbstractCheck {

  private int commentsCount = 0; //default counter is zero

  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
  }

  //necessary override so the tree visits comment nodes
  @Override
  public boolean isCommentNodesRequired() {
    return true;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
    //increase comments
    commentsCount++;
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    //display # of comments to the user
    String message = "Number of comments: " + this.commentsCount;
    log(ast.getLineNo(), message);
    commentsCount = 0;
  }
}
