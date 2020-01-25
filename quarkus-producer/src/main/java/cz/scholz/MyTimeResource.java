package cz.scholz;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Set;

@Path("/time")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyTimeResource {
    @Inject
    @Channel("time-from-post")
    Emitter<MyTime> myTimeEmitter;

    @POST
    public Set<MyTime> add(MyTime time) {
        myTimeEmitter.send(time);
        return Collections.singleton(time);
    }
}
