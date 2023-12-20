import React from "react";
import { TodoItem } from "./TodoItem";
import { Todo,ToggleTodo} from "./types";

interface TodoListProps{
    todos:Todo[],
    toggleTodo:ToggleTodo
}

export const TodoList:React.FC<TodoListProps> = ({todos,toggleTodo})=>{
    return(
        <ul>
            {todos.map(todo=>{
                return <TodoItem key={todo.text} todo={todo} toggleTodo={toggleTodo}/>
            })}
        </ul>
    )
}