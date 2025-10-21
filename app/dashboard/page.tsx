"use client"

import { useEffect, useState } from "react"
import { useRouter } from "next/navigation"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Alert, AlertDescription } from "@/components/ui/alert"
import { LogOut, BookOpen, Calendar, Upload } from "lucide-react"

interface User {
  userId: number
  username: string
  fullName: string
  role: string
  email: string
}

interface Material {
  materialId: number
  title: string
  description: string
  fileType: string
  uploadedBy: string
  uploadDate: string
}

interface Event {
  eventId: number
  eventName: string
  description: string
  eventDate: string
  location: string
}

export default function DashboardPage() {
  const [user, setUser] = useState<User | null>(null)
  const [materials, setMaterials] = useState<Material[]>([])
  const [events, setEvents] = useState<Event[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState("")
  const router = useRouter()

  useEffect(() => {
    const userData = localStorage.getItem("user")
    if (!userData) {
      router.push("/login")
      return
    }

    const parsedUser = JSON.parse(userData)
    setUser(parsedUser)
    fetchMaterials()
    fetchEvents()
  }, [router])

  const fetchMaterials = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/materials")
      const data = await response.json()
      setMaterials(Array.isArray(data) ? data : [])
    } catch (err) {
      console.error("Error fetching materials:", err)
      setError("Failed to load materials")
    } finally {
      setLoading(false)
    }
  }

  const fetchEvents = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/events")
      const data = await response.json()
      setEvents(Array.isArray(data) ? data : [])
    } catch (err) {
      console.error("Error fetching events:", err)
    }
  }

  const handleLogout = () => {
    localStorage.removeItem("user")
    router.push("/login")
  }

  if (!user) {
    return null
  }

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <header className="bg-white border-b">
        <div className="max-w-7xl mx-auto px-4 py-4 flex justify-between items-center">
          <div>
            <h1 className="text-2xl font-bold text-gray-900">Study Material Sharing</h1>
            <p className="text-gray-600">Welcome, {user.fullName}</p>
          </div>
          <Button variant="outline" onClick={handleLogout} className="gap-2 bg-transparent">
            <LogOut className="w-4 h-4" />
            Logout
          </Button>
        </div>
      </header>

      {/* Main Content */}
      <main className="max-w-7xl mx-auto px-4 py-8">
        {error && (
          <Alert variant="destructive" className="mb-4">
            <AlertDescription>{error}</AlertDescription>
          </Alert>
        )}

        {/* Stats Cards */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-8">
          <Card>
            <CardHeader className="pb-2">
              <CardTitle className="text-sm font-medium text-gray-600">Total Materials</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">{materials.length}</div>
            </CardContent>
          </Card>
          <Card>
            <CardHeader className="pb-2">
              <CardTitle className="text-sm font-medium text-gray-600">Upcoming Events</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">{events.length}</div>
            </CardContent>
          </Card>
          <Card>
            <CardHeader className="pb-2">
              <CardTitle className="text-sm font-medium text-gray-600">Your Role</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-lg font-semibold capitalize">{user.role}</div>
            </CardContent>
          </Card>
        </div>

        {/* Tabs */}
        <Tabs defaultValue="materials" className="space-y-4">
          <TabsList>
            <TabsTrigger value="materials" className="gap-2">
              <BookOpen className="w-4 h-4" />
              Materials
            </TabsTrigger>
            <TabsTrigger value="events" className="gap-2">
              <Calendar className="w-4 h-4" />
              Events
            </TabsTrigger>
            {user.role === "faculty" && (
              <TabsTrigger value="upload" className="gap-2">
                <Upload className="w-4 h-4" />
                Upload Material
              </TabsTrigger>
            )}
          </TabsList>

          {/* Materials Tab */}
          <TabsContent value="materials">
            <Card>
              <CardHeader>
                <CardTitle>Study Materials</CardTitle>
                <CardDescription>Browse and download available study materials</CardDescription>
              </CardHeader>
              <CardContent>
                {loading ? (
                  <p className="text-gray-500">Loading materials...</p>
                ) : materials.length === 0 ? (
                  <p className="text-gray-500">No materials available yet</p>
                ) : (
                  <div className="space-y-3">
                    {materials.map((material) => (
                      <div key={material.materialId} className="p-4 border rounded-lg hover:bg-gray-50">
                        <div className="flex justify-between items-start">
                          <div>
                            <h3 className="font-semibold text-gray-900">{material.title}</h3>
                            <p className="text-sm text-gray-600">{material.description}</p>
                            <div className="flex gap-4 mt-2 text-xs text-gray-500">
                              <span>Type: {material.fileType}</span>
                              <span>By: {material.uploadedBy}</span>
                              <span>Date: {new Date(material.uploadDate).toLocaleDateString()}</span>
                            </div>
                          </div>
                          <Button variant="outline" size="sm">
                            Download
                          </Button>
                        </div>
                      </div>
                    ))}
                  </div>
                )}
              </CardContent>
            </Card>
          </TabsContent>

          {/* Events Tab */}
          <TabsContent value="events">
            <Card>
              <CardHeader>
                <CardTitle>Upcoming Events</CardTitle>
                <CardDescription>View and register for events</CardDescription>
              </CardHeader>
              <CardContent>
                {events.length === 0 ? (
                  <p className="text-gray-500">No events scheduled</p>
                ) : (
                  <div className="space-y-3">
                    {events.map((event) => (
                      <div key={event.eventId} className="p-4 border rounded-lg hover:bg-gray-50">
                        <div className="flex justify-between items-start">
                          <div>
                            <h3 className="font-semibold text-gray-900">{event.eventName}</h3>
                            <p className="text-sm text-gray-600">{event.description}</p>
                            <div className="flex gap-4 mt-2 text-xs text-gray-500">
                              <span>Date: {new Date(event.eventDate).toLocaleDateString()}</span>
                              <span>Location: {event.location}</span>
                            </div>
                          </div>
                          <Button variant="outline" size="sm">
                            Register
                          </Button>
                        </div>
                      </div>
                    ))}
                  </div>
                )}
              </CardContent>
            </Card>
          </TabsContent>

          {/* Upload Tab (Faculty Only) */}
          {user.role === "faculty" && (
            <TabsContent value="upload">
              <Card>
                <CardHeader>
                  <CardTitle>Upload Study Material</CardTitle>
                  <CardDescription>Share new study materials with students</CardDescription>
                </CardHeader>
                <CardContent>
                  <div className="border-2 border-dashed rounded-lg p-8 text-center">
                    <Upload className="w-12 h-12 mx-auto text-gray-400 mb-2" />
                    <p className="text-gray-600">Drag and drop files here or click to browse</p>
                    <p className="text-xs text-gray-500 mt-1">Supported: PDF, DOC, PPT (Max 50MB)</p>
                    <Button className="mt-4">Select Files</Button>
                  </div>
                </CardContent>
              </Card>
            </TabsContent>
          )}
        </Tabs>
      </main>
    </div>
  )
}
