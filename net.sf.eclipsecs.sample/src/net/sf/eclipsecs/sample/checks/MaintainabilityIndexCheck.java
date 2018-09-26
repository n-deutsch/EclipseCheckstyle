package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//Calculates Maintainability Index
//Maintainability Index = 
//171 - 5.2 * log2(Halstead Volume) - 0.23 * Cyclomatic Complexity - 
//16.2 * log2(line count) + 50 * sin(sqrt(2.4*comment percentage))
public class MaintainabilityIndexCheck extends AbstractCheck {

  private int HalsteadVolume = 0;
  private int CyclomaticComplexity = 0;
  private int LineCount = 0;
  private double CommentPercentage = 0;
  private double MaintainabilityIndex = 0;

  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.CLASS_DEF };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.CLASS_DEF };
  }

  @Override
  public void visitToken(DetailAST ast) {
    //Maintainability Index = 
    //171 - 5.2 * log2(Halstead Volume) - 0.23 * Cyclomatic Complexity - 
    //16.2 * log2(line count) + 50 * sin(sqrt(2.4*comment percentage))
    HalsteadVolume = 1;
    CyclomaticComplexity = 2;
    LineCount = 3;
    CommentPercentage = 0.25;
    
    MaintainabilityIndex = 171 - 5.2 * (Math.log(HalsteadVolume)/Math.log(2)) - 0.23 *CyclomaticComplexity - 16.2 * (Math.log(LineCount)/Math.log(2)) + 50 * Math.sin(Math.sqrt(2.4 * CommentPercentage));
    
    String message = "Maintainability Index: " + this.MaintainabilityIndex;
    log(ast.getLineNo(), message);
  }
}