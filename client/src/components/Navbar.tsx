import { Logo } from './Logo';
import { Button } from '@/components/ui/button';
import { RotateCcw, Info } from 'lucide-react';

interface NavbarProps {
  onNewGame: () => void;
  onShowRules: () => void;
}

export function Navbar({ onNewGame, onShowRules }: NavbarProps) {
  return (
    <nav 
      className="fixed top-0 left-0 right-0 z-50 px-4 py-3 flex justify-between items-center bg-[#0a0a1a]/90 backdrop-blur-md border-b border-white/10"
      data-testid="navbar"
    >
      <Logo size="normal" />
      
      <div className="flex items-center gap-2">
        <Button
          variant="ghost"
          size="sm"
          onClick={onShowRules}
          className="text-white/70 hover:text-white hover:bg-white/10"
          data-testid="rules-button"
        >
          <Info className="w-4 h-4 mr-1.5" />
          <span className="hidden sm:inline">Rules</span>
        </Button>
        <Button
          variant="ghost"
          size="sm"
          onClick={onNewGame}
          className="text-white/70 hover:text-white hover:bg-white/10"
          data-testid="new-game-button"
        >
          <RotateCcw className="w-4 h-4 mr-1.5" />
          <span className="hidden sm:inline">New Game</span>
        </Button>
      </div>
    </nav>
  );
}
