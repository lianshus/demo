import React, { ChangeEvent, useState } from "react";

import { Todo, AddTodo } from "./types";

interface AddItemProps {
    addTodo: AddTodo
}

export const AddItem: React.FC<AddItemProps> = ({ addTodo }) => {
    const [newTodo, setNewTodo] = useState("");

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setNewTodo(e.target.value);
    }

    return (
        <>
            <input type="text" value={newTodo} onChange={handleChange} />
            <button onClick={() => addTodo(newTodo)}>
                Add Todo
            </button>
        </>

    )
}