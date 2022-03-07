package learn.spaceflight;

import learn.spaceflight.personnel.Astronaut;
import learn.spaceflight.spacecraft.InterstellarTransport;
import learn.spaceflight.spacecraft.MoonHopper;
import learn.spaceflight.spacecraft.Probe;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        /*
        // 1. An ApplicationContext is the DI container.
        // 2. The ClassPathXmlApplicationContext is a specific type of DI container.
        // It requires an XML configuration file to register beans or object instances.
        // Here, we use "dependency-configuration.xml" but the file can be called whatever we like as long
        // as it uses the correct XML schema.
        // 3. The container knows about an Astronaut, so we ask it for one.
        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-configuration.xml");

        Astronaut captain = container.getBean("captain", Astronaut.class);
        Astronaut notTheCaptain = container.getBean("crew", Astronaut.class);

        System.out.println("Captain: " + captain);
        System.out.println("Not the Captain: " + notTheCaptain);
        */

        /*
        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-configuration.xml");

        MoonHopper hopper = container.getBean(MoonHopper.class);
        System.out.println(hopper);
        */

        /*
        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-configuration.xml");

        InterstellarTransport transport = container.getBean(InterstellarTransport.class);
        System.out.println(transport);
        */

        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-configuration.xml");

        Probe one = container.getBean(Probe.class);
        Probe two = container.getBean(Probe.class);

        // Add distance to one.
        one.addDistance(1000);

        System.out.println("one: " + one);
        System.out.println("two: " + two);

        // Add distance to two.
        two.addDistance(2589);

        System.out.println("one: " + one);
        System.out.println("two: " + two);

    }
}