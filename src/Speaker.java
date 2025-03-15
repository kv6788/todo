import java.util.HashSet;

public interface Speaker {
    void addListener(Listener e);
    void removeListener(Listener e);
    void speech();
}
