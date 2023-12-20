import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';

import { Todo, ToggleTodo, AddTodo } from './types'
import { TodoList } from './TodoList'
import { AddItem } from './AddItem';

const init: Todo[] = [
  {
    text: "run",
    complete: true
  },
  {
    text: "eat",
    complete: false
  },
]

const App: React.FC = () => {
  const [todos, setTodos] = useState(init);

  const toggleTodo: ToggleTodo = (selectedTodo: Todo) => {
    const newTodos = todos.map(todo => {
      if (todo === selectedTodo) {
        return {
          ...todo,
          complete: !todo.complete
        }
      }
      return todo;
    })
    setTodos(newTodos);
  }

  const addTodo: AddTodo = addItem => {
    if (addItem.trim() === "")
      return;
    setTodos([
      ...todos,
      { text: addItem, complete: false }
    ])
  }

  return (
    <div className="App">
      <TodoList todos={todos} toggleTodo={toggleTodo} />
      <AddItem addTodo={addTodo}/>
    </div>
  );
}

export default App;
