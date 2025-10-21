"use client"

import { useEffect, useState } from "react"
import { useRouter } from "next/navigation"

export interface AuthUser {
  userId: number
  username: string
  fullName: string
  role: string
  email: string
}

export function useAuth() {
  const [user, setUser] = useState<AuthUser | null>(null)
  const [loading, setLoading] = useState(true)
  const router = useRouter()

  useEffect(() => {
    const userData = localStorage.getItem("user")
    if (userData) {
      try {
        setUser(JSON.parse(userData))
      } catch (err) {
        console.error("Failed to parse user data:", err)
        localStorage.removeItem("user")
        router.push("/login")
      }
    }
    setLoading(false)
  }, [router])

  const logout = () => {
    localStorage.removeItem("user")
    setUser(null)
    router.push("/login")
  }

  return { user, loading, logout }
}
