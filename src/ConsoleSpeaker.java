import java.util.HashSet;
import java.util.Scanner;

public class ConsoleSpeaker implements Speaker {
    private HashSet<Listener> listeners = new HashSet<>();
    private String value;
    @Override
    public void addListener(Listener e) {
        listeners.add(e);
    }

    @Override
    public void removeListener(Listener e) {
        listeners.remove(e);
    }

    @Override
    public void speech() {
        for(var e : listeners ) e.listen(this.value);
    }
    public ConsoleSpeaker() {
        this.addListener(CommandHandler.get());
        var scan = new Scanner(System.in);
        for(;;) {
            System.out.print("> ");
            this.value = scan.nextLine();
            if(this.value != null) this.speech();
            else System.exit(1);
        }
    }
}
