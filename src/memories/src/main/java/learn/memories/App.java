package learn.memories;

import learn.memories.data.MemoryFileRepository;
import learn.memories.domain.MemoryService;
import learn.memories.ui.ConsoleIO;
import learn.memories.ui.Controller;
import learn.memories.ui.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@ComponentScan // 2. Tells Spring to scan all classes in this package or its children.
// A component scan can only look "down" into children. It can never look up into parents or across into sibling packages.

@PropertySource("classpath:data.properties") /* Before we call it a day, consider the limitations of our current
annotation configuration. As configured, our application can read from one and only one data file. The file path is
hard-coded into the application. That's not great. Typically, we want data to be something that lives outside of our
code -- something that our code responds to but isn't part of the code.

The developers who designed annotation configuration thought of that. They created a @PropertySource annotation that
reads data from an external properties file. A property source defines a source for the entire application, so we can
add it to our App class.

We'll add the data.properties file to the src/resources/ directory.*/
public class App {

    public static void main(String[] args) {

        /*
        // "./data/memories.txt" is the path to our "production" data file.
        MemoryFileRepository repository = new MemoryFileRepository("./data/memories.txt");
        // repository satisfies the MemoryRepository dependency.
        MemoryService service = new MemoryService(repository);

        ConsoleIO io = new ConsoleIO();
        // io satisfies the TextIO dependency.
        View view = new View(io);

        // view satisfies the View dependency
        // service satisfies the MemoryService dependency
        Controller controller = new Controller(view, service);
        */


        /*
        // using Spring XML:
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency-configuration.xml");
        Controller controller = context.getBean(Controller.class);
        // Run the app!
        controller.run();
        */



        //using Spring Annotations
        // 1. We pass the App.class, this class, as a constructor argument.
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        // 3. The context works the same as the XML context.
        Controller controller = context.getBean(Controller.class);
        // Run the app!
        controller.run();

    }

}
