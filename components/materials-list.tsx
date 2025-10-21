"use client"

import type { Material } from "@/lib/api"
import { Button } from "@/components/ui/button"
import { Download, FileText } from "lucide-react"

interface MaterialsListProps {
  materials: Material[]
  loading: boolean
}

export function MaterialsList({ materials, loading }: MaterialsListProps) {
  if (loading) {
    return <div className="text-center py-8 text-gray-500">Loading materials...</div>
  }

  if (materials.length === 0) {
    return <div className="text-center py-8 text-gray-500">No materials available yet</div>
  }

  return (
    <div className="space-y-3">
      {materials.map((material) => (
        <div key={material.materialId} className="p-4 border rounded-lg hover:bg-gray-50 transition">
          <div className="flex items-start justify-between gap-4">
            <div className="flex items-start gap-3 flex-1">
              <FileText className="w-5 h-5 text-blue-600 mt-1 flex-shrink-0" />
              <div className="flex-1 min-w-0">
                <h3 className="font-semibold text-gray-900 break-words">{material.title}</h3>
                <p className="text-sm text-gray-600 mt-1">{material.description}</p>
                <div className="flex flex-wrap gap-3 mt-2 text-xs text-gray-500">
                  <span className="bg-gray-100 px-2 py-1 rounded">{material.fileType}</span>
                  <span>By: {material.uploadedBy}</span>
                  <span>{new Date(material.uploadDate).toLocaleDateString()}</span>
                </div>
              </div>
            </div>
            <Button variant="outline" size="sm" className="flex-shrink-0 gap-2 bg-transparent">
              <Download className="w-4 h-4" />
              <span className="hidden sm:inline">Download</span>
            </Button>
          </div>
        </div>
      ))}
    </div>
  )
}
