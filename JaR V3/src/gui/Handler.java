package gui;

import game.AnzeigeApfel;
import game.Leben;
import game.Level;
import game.LevelCreator;
import game.Player;
import states.State;

public class Handler
{
  private FrameMain frameMain;
 
  private State stateGame;
  private State stateHauptMenue;
  private State stateEditor;
  private State stateLevelSelect;
  private State stateOptionen;
  private Player player;
  private PanelGame panelGame;
  private LevelCreator levelCreator;
  private AnzeigeApfel anzeigeApfel;
  private Level level;
  private Leben leben;
  
  public Handler(FrameMain frameMain)
  {
    this.frameMain = frameMain;
  }

  public FrameMain getFrameMain()
  {
    return frameMain;
  }

 

  public Leben getLeben()
  {
    return leben;
  }

  public void setLeben(Leben leben)
  {
    this.leben = leben;
  }

  public State getStateGame()
  {
    return stateGame;
  }

  public void setStateGame(State stateGame)
  {
    this.stateGame = stateGame;
  }

  public State getStateHauptMenue()
  {
    return stateHauptMenue;
  }

  public void setStateHauptMenue(State stateHauptMenue)
  {
    this.stateHauptMenue = stateHauptMenue;
  }

  public State getStateEditor()
  {
    return stateEditor;
  }

  public void setStateEditor(State stateEditor)
  {
    this.stateEditor = stateEditor;
  }

  public State getStateLevelSelect()
  {
    return stateLevelSelect;
  }

  public void setStateLevelSelect(State stateLevelSelect)
  {
    this.stateLevelSelect = stateLevelSelect;
  }

  public State getStateOptionen()
  {
    return stateOptionen;
  }

  public void setStateOptionen(State stateOptionen)
  {
    this.stateOptionen = stateOptionen;
  }

  public State getStatePause()
  {
    return statePause;
  }

  public void setStatePause(State statePause)
  {
    this.statePause = statePause;
  }

  public Player getPlayer()
  {
    return player;
  }

  public void setPlayer(Player player)
  {
    this.player = player;
  }
  
  
  public PanelGame getPanelGame()
  {
    return panelGame;
  }

  public void setPanelGame(PanelGame panelGame)
  {
   
    this.panelGame = panelGame;
  }

  

  public LevelCreator getLevelCreator()
  {
    return levelCreator;
  }

  public void setLevelCreator(LevelCreator levelCreator)
  {
    
    this.levelCreator = levelCreator;
  }



  private State statePause;

  public AnzeigeApfel getAnzeigeApfel()
  {
    return anzeigeApfel;
  }

  public void setAnzeigeApfel(AnzeigeApfel anzeigeApfel)
  {
    this.anzeigeApfel = anzeigeApfel;
  }

  public Level getLevel()
  {
    return level;
  }

  public void setLevel(Level level)
  {
    this.level = level;
  }

  
}
