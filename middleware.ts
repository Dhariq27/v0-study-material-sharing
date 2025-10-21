import { NextResponse } from "next/server"
import type { NextRequest } from "next/server"

export function middleware(request: NextRequest) {
  const pathname = request.nextUrl.pathname

  // Public routes that don't require authentication
  const publicRoutes = ["/login", "/register"]

  // Check if the route is public
  if (publicRoutes.includes(pathname)) {
    return NextResponse.next()
  }

  // For protected routes, we can't check localStorage in middleware
  // So we'll handle auth checks in the page components
  return NextResponse.next()
}

export const config = {
  matcher: ["/((?!_next/static|_next/image|favicon.ico).*)"],
}
