import React from "react";
import { Todo, ToggleTodo } from "./types";

interface TodoListItem {
    todo:Todo
    toggleTodo:ToggleTodo
}

export const TodoItem: React.FC<TodoListItem> = ({todo,toggleTodo}) => {
    return (
        <li>
            <input type="checkbox" checked={todo.complete} onClick={()=>toggleTodo(todo)}/>
            {todo.text}
        </li>
    )
}