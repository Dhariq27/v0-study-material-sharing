"use client"

import type { Event } from "@/lib/api"
import { Button } from "@/components/ui/button"
import { Calendar, MapPin } from "lucide-react"

interface EventsListProps {
  events: Event[]
  loading: boolean
}

export function EventsList({ events, loading }: EventsListProps) {
  if (loading) {
    return <div className="text-center py-8 text-gray-500">Loading events...</div>
  }

  if (events.length === 0) {
    return <div className="text-center py-8 text-gray-500">No events scheduled</div>
  }

  return (
    <div className="space-y-3">
      {events.map((event) => (
        <div key={event.eventId} className="p-4 border rounded-lg hover:bg-gray-50 transition">
          <div className="flex items-start justify-between gap-4">
            <div className="flex-1">
              <h3 className="font-semibold text-gray-900">{event.eventName}</h3>
              <p className="text-sm text-gray-600 mt-1">{event.description}</p>
              <div className="flex flex-wrap gap-4 mt-3 text-sm text-gray-600">
                <div className="flex items-center gap-1">
                  <Calendar className="w-4 h-4" />
                  {new Date(event.eventDate).toLocaleDateString()}
                </div>
                <div className="flex items-center gap-1">
                  <MapPin className="w-4 h-4" />
                  {event.location}
                </div>
              </div>
            </div>
            <Button variant="outline" size="sm" className="flex-shrink-0 bg-transparent">
              Register
            </Button>
          </div>
        </div>
      ))}
    </div>
  )
}
