const API_URL = 'http://localhost:3000/tasks'

export const getTasks = () => {
  return fetch(API_URL).then(response => response.json())
}

export const getTaskById = (id) => {
  return fetch(`${API_URL}/${id}`).then(response => response.json())
}

export const createTask = (task) => {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(task)
  }).then(response => response.json())
}

export const updateTaskById = (id, task) => {
  return fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(task)
  }).then(response => response.json())
}

export const deleteTaskById = (id) => {
  return fetch(`${API_URL}/${id}`, {
    method: 'DELETE'
  }).then(response => response.json())
}