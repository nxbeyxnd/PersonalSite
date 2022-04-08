package ru.alexey.site.components.memento;
/* 
08.04.2022: Alexey created this file inside the package: ru.alexey.site.components.memento
*/

import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

@Component
public class PostSessionHistory {
    private final Deque<PostMemento> history = new LinkedList();


    public void save(PostMemento post) {
        history.addLast(post);
    }

    public Optional<PostMemento> load() {
        return Optional.ofNullable(history.pollLast());
    }
}
