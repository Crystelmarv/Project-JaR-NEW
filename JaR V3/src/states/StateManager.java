package states;

public class StateManager
{
  private static State activeState = null;

  public static void setState(State state)
  {
    activeState = state;
  }

  public static State getState()
  {
    return activeState;
  }

}
