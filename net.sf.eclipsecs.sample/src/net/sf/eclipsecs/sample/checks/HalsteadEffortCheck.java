package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//calculates the Halstead Effort
//Halstead Effort = Halstead Difficulty * Halstead Volume
public class HalsteadEffortCheck extends AbstractCheck {

  private int HalsteadDifficulty = 0;
  private int HalsteadVolume = 0;
  
  private int HalsteadEffort = 0;

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
    HalsteadDifficulty = 25; //this is a placeholder for halsteadDifficulty
    HalsteadVolume = 75; //this is a placeholder for halsteadVolume
    
    HalsteadEffort = HalsteadDifficulty * HalsteadVolume;
    String message = "Halstead Effort: " + HalsteadEffort;
    log(ast.getLineNo(), message);
  }
}