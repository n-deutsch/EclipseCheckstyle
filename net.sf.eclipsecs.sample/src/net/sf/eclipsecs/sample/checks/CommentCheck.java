/*package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts the total number of expressions
public class CommentCheck extends AbstractCheck {

  private int commentCount = 0;

  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_END };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_END };
  }

  @Override
  public void visitToken(DetailAST ast) {
    commentCount++;
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    log(ast.getLineNo(), "Number of comments: " + this.commentCount);
    commentCount = 0;
  }
}
*/