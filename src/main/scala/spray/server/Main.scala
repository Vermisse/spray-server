package spray.server

import akka.actor.{Props, ActorSystem}
import spray.servlet._

// This class is instantiated by the servlet initializer.
// It can either define a constructor with a single
// `javax.servlet.ServletContext` parameter or a
// default constructor.
// It must mplement the spray.servlet.WebBoot trait.
class Main extends WebBoot {

  // we need an ActorSystem to host our application in
  val system = ActorSystem("example")

  // the service actor replies to incoming HttpRequests
  val serviceActor = system.actorOf(Props[Router])

  system.registerOnTermination {
    // put additional cleanup code here
    system.log.info("Application shut down")
  }
}