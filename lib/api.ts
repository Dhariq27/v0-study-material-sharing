const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080/api"

export interface LoginRequest {
  username: string
  password: string
}

export interface RegisterRequest {
  username: string
  email: string
  password: string
  fullName: string
  role: string
}

export interface User {
  userId: number
  username: string
  fullName: string
  role: string
  email: string
}

export interface Material {
  materialId: number
  title: string
  description: string
  fileType: string
  uploadedBy: string
  uploadDate: string
}

export interface Event {
  eventId: number
  eventName: string
  description: string
  eventDate: string
  location: string
}

async function handleResponse(response: Response) {
  if (!response.ok) {
    const errorData = await response.json().catch(() => ({}))
    throw new Error(errorData.message || `HTTP ${response.status}: ${response.statusText}`)
  }
  return response.json()
}

// Auth API calls
export async function login(credentials: LoginRequest) {
  try {
    const response = await fetch(`${API_BASE_URL}/auth/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(credentials),
    })
    return await handleResponse(response)
  } catch (error) {
    console.error("[v0] Login API error:", error)
    throw error
  }
}

export async function register(data: RegisterRequest) {
  try {
    const response = await fetch(`${API_BASE_URL}/auth/register`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    })
    return await handleResponse(response)
  } catch (error) {
    console.error("[v0] Register API error:", error)
    throw error
  }
}

// Materials API calls
export async function getMaterials() {
  try {
    const response = await fetch(`${API_BASE_URL}/materials`)
    return await handleResponse(response)
  } catch (error) {
    console.error("[v0] Get materials API error:", error)
    throw error
  }
}

export async function getMaterialById(id: number) {
  try {
    const response = await fetch(`${API_BASE_URL}/materials/${id}`)
    return await handleResponse(response)
  } catch (error) {
    console.error("[v0] Get material API error:", error)
    throw error
  }
}

export async function createMaterial(material: Partial<Material>) {
  try {
    const response = await fetch(`${API_BASE_URL}/materials`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(material),
    })
    return await handleResponse(response)
  } catch (error) {
    console.error("[v0] Create material API error:", error)
    throw error
  }
}

// Events API calls
export async function getEvents() {
  try {
    const response = await fetch(`${API_BASE_URL}/events`)
    return await handleResponse(response)
  } catch (error) {
    console.error("[v0] Get events API error:", error)
    throw error
  }
}

export async function getEventById(id: number) {
  try {
    const response = await fetch(`${API_BASE_URL}/events/${id}`)
    return await handleResponse(response)
  } catch (error) {
    console.error("[v0] Get event API error:", error)
    throw error
  }
}

export async function createEvent(event: Partial<Event>) {
  try {
    const response = await fetch(`${API_BASE_URL}/events`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(event),
    })
    return await handleResponse(response)
  } catch (error) {
    console.error("[v0] Create event API error:", error)
    throw error
  }
}
