# Chess 2 - Design Guidelines

## Design Approach
Modern game interface with dramatic gradient aesthetics and atmospheric effects. The design draws from the provided CSS's sophisticated dark theme with vibrant blue-red gradient system, creating an immersive chess experience.

## Color Palette (From Provided CSS)
- **Primary Blue**: #4facfe → #00f2fe (gradient)
- **Primary Red**: #f5576c → #f093fb (gradient)
- **Dark Background**: #0a0a1a
- **Darker Background**: #050510
- **Card Background**: rgba(255, 255, 255, 0.05)
- **Text Primary**: #ffffff
- **Text Secondary**: rgba(255, 255, 255, 0.7)
- **Gold**: #ffd700 (for premium/special states)

## Typography
- **Font Family**: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif
- **Branding**: "Chess" in blue gradient, "2" in red gradient (larger, 8rem vs 5rem)
- **Hierarchy**: Bold weights (700-800) for headers, 500-600 for UI elements, 400 for body text
- **Sizes**: Hero text 5-8rem, section headers 2.5-3rem, body 1-1.3rem, UI labels 0.9-1rem

## Layout System
- **Spacing Units**: Consistent with Tailwind-style spacing: 4px (1), 8px (2), 16px (4), 32px (8), 64px (16)
- **Container**: Max-width 1400px, centered with 5% horizontal padding
- **Game Board**: Centered focal point, 600x600px on desktop, responsive scaling on mobile
- **Side Panels**: Flanking board layout - captured pieces (left), move history (right)

## Component Library

### Navigation Bar
- Fixed top position with dark background (rgba(10, 10, 26, 0.9))
- Backdrop blur effect (10px)
- Logo: "Chess" (blue gradient) + "2" (red gradient) 
- Minimal navigation: New Game, Rules, Settings
- Bottom border: 1px rgba(255, 255, 255, 0.1)

### Game Board
- 8x8 grid with alternating square colors
- Light squares: rgba(255, 255, 255, 0.08)
- Dark squares: rgba(255, 255, 255, 0.02)
- Board glow: Subtle blue-red gradient blur (40px) around board edges
- Selected piece highlight: Blue gradient border with glow
- Valid move indicators: Small circles with blue gradient fill
- Check indicator: Red gradient pulse on king's square
- Hover states: Subtle brightness increase on squares

### Chess Pieces
- Use Unicode chess symbols (♔♕♖♗♘♙ for white, ♚♛♜♝♞♟ for black)
- White pieces: #ffffff with subtle drop shadow
- Black pieces: #888888 with gradient tint
- Size: 60-70% of square size for visual breathing room
- Drag state: Scale 1.1, elevated shadow, cursor pointer

### Status Panel (Top)
- Current player indicator with colored background glow
- White's turn: Blue gradient background
- Black's turn: Red gradient background  
- Game status messages: "Check!", "Checkmate!", "Stalemate!" with appropriate color coding
- Timer display (if implemented) with digital font styling

### Captured Pieces Display
- Semi-transparent card container (rgba(255, 255, 255, 0.05))
- Pieces arranged in grid, smaller size (30px)
- Count badges with gradient backgrounds
- White captures: Left panel with blue accent
- Black captures: Right panel with red accent

### Move History Panel
- Scrollable list with alternating row backgrounds
- Move notation in monospace-style format
- Current move highlighted with gradient
- Click to review previous positions (optional enhancement)

### Buttons & Controls
- Primary action (New Game): Blue gradient (#4facfe → #00f2fe), box-shadow with blue glow
- Secondary actions: Transparent with border, hover state adds red gradient background
- Rounded corners (50px border-radius for pill shape)
- Padding: 1rem vertical, 2rem horizontal
- Hover: translateY(-3px) with enhanced shadow

### Particles Background
- Animated particles floating across viewport
- 4px circular particles with blue-red gradient
- Opacity 0.6, 15s float animation
- Fixed position, pointer-events none, z-index 0

## Animations & Effects
- **Board Float**: Subtle 6s ease-in-out vertical movement
- **Piece Selection**: Scale and glow transition 0.3s
- **Glow Effects**: Pulsing box-shadows on active elements
- **Badge Animations**: Glowing 2s infinite for status indicators
- **Hover Transitions**: 0.3s ease for all interactive elements
- **Victory/Defeat**: Dramatic gradient overlay with message modal

## Game States
- **Normal Play**: Board centered, side panels visible
- **Check**: King square pulses red, audio cue
- **Checkmate/Stalemate**: Overlay modal with gradient background, game result, play again button
- **Pawn Promotion**: Modal with piece selection (Queen, Rook, Bishop, Knight) using gradient cards
- **Loading**: Subtle particle animation with "Chess 2" logo fade-in

## Responsive Behavior
- Desktop (1024px+): Three-column layout (captured | board | history)
- Tablet (768px-1023px): Two-column (board + stacked side panels)
- Mobile (<768px): Single column, board takes full width, collapsible panels

## Accessibility
- Keyboard navigation for piece selection and movement
- ARIA labels for all interactive elements
- High contrast maintained despite dark theme
- Focus indicators with visible gradient outlines

## Images
No hero images required. This is a functional game interface. Visual interest comes from gradient effects, glowing elements, and animated particles background.