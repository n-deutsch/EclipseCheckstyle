package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

//counts number of looping statements
public class TestLoopingStatementsCheck {
  
  private LoopingStatementsCheck lsc = new LoopingStatementsCheck();
  
  @Mock
  private LoopingStatementsCheck lscMock;
  
  //functional test
  public boolean integrationTest() {
    //mock our test object
    
    DetailAST ast = new DetailAST();
    ast.setType(TokenTypes.LITERAL_WHILE);
    
    //stub finishTree
    doAnswer(new Answer<Void>() {
      @Override
      public Void answer(InvocationOnMock invocation) throws Throwable{
        stubFinishTree();
        return null;
      }
    }).when(lscMock).finishTree(any(DetailAST.class));
    
    //run tests on visitToken() and stubbed finishTree()
    lscMock.visitToken(null);
    lscMock.finishTree(null);
    assertEquals(0, lscMock.getLoopingStatements());
    
    lscMock.visitToken(ast);
    lscMock.finishTree(ast);
    assertEquals(0, lscMock.getLoopingStatements());
    
    
    //unstub finishTree
    reset(lscMock);
    
    //run tests on visitToken() and finishTree()
    lscMock.visitToken(null);
    lscMock.finishTree(null);
    assertEquals(0, lscMock.getLoopingStatements());
    
    lscMock.visitToken(ast);
    lscMock.finishTree(ast);
    assertEquals(0, lscMock.getLoopingStatements());
    
    return true;
  }
  
  @Test
  public boolean testVisitToken() {
    //test the visitToken() method
    
    //test the method with null AST
    lsc.visitToken(null);
    assertEquals(0,lsc.getLoopingStatements());
    
    //test it with a real AST
    DetailAST ast = new DetailAST();
    ast.setType(TokenTypes.LITERAL_FOR);
    lsc.visitToken(ast);
    assertEquals(1,lsc.getLoopingStatements());
    
    return true;
  }
  
  @Test
  public boolean testFinishTree() {
    //test the method with null
    lsc.finishTree(null);
    assertEquals(0,lsc.getLoopingStatements());
    
    //test it with a real AST
    DetailAST ast = new DetailAST();
    lsc.finishTree(ast);
    assertEquals(0,lsc.getLoopingStatements());
    
    return true;
  }
  
  /*THIS STUB NEVER USED
  private void stubVisitToken(boolean increase) {
    int c = 0;
    //increase by one
    if(increase) {
      c = lscMock.getLoopingStatements();
      lscMock.setLoopingStatements(c + 1);
    }
  }
  */
  
  private void stubFinishTree() {
    //finish tree always sets to zero
    lscMock.setLoopingStatements(0);
  }
}