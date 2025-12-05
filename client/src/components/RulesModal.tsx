import { Button } from '@/components/ui/button';
import { ScrollArea } from '@/components/ui/scroll-area';
import { X } from 'lucide-react';

interface RulesModalProps {
  onClose: () => void;
}

export function RulesModal({ onClose }: RulesModalProps) {
  return (
    <div 
      className="fixed inset-0 z-50 flex items-center justify-center bg-black/80 backdrop-blur-md p-4"
      data-testid="rules-modal"
    >
      <div className="relative bg-gradient-to-br from-[#0a0a1a] to-[#151530] border border-white/10 rounded-2xl shadow-2xl max-w-lg w-full max-h-[80vh] overflow-hidden">
        <div className="absolute inset-0 opacity-10">
          <div className="absolute top-0 left-0 w-40 h-40 bg-[#4facfe] rounded-full blur-[80px]" />
          <div className="absolute bottom-0 right-0 w-40 h-40 bg-[#f5576c] rounded-full blur-[80px]" />
        </div>
        
        <div className="relative z-10">
          <div className="flex items-center justify-between p-4 border-b border-white/10">
            <h2 className="text-xl font-bold text-white">How to Play Chess</h2>
            <Button 
              variant="ghost" 
              size="icon" 
              onClick={onClose}
              className="text-white/60 hover:text-white"
              data-testid="close-rules-button"
            >
              <X className="w-5 h-5" />
            </Button>
          </div>
          
          <ScrollArea className="h-[60vh] p-4">
            <div className="space-y-4 text-white/80 text-sm pr-4">
              <section>
                <h3 className="text-base font-semibold text-white mb-2 flex items-center gap-2">
                  <span className="text-xl">&#9812;</span> Objective
                </h3>
                <p>Checkmate your opponent's King. This occurs when the King is under attack and cannot escape.</p>
              </section>

              <section>
                <h3 className="text-base font-semibold text-white mb-2 flex items-center gap-2">
                  <span className="text-xl">&#9817;</span> Piece Movements
                </h3>
                <ul className="space-y-2">
                  <li><strong className="text-white">King &#9812;</strong> - Moves one square in any direction</li>
                  <li><strong className="text-white">Queen &#9813;</strong> - Moves any number of squares in any direction</li>
                  <li><strong className="text-white">Rook &#9814;</strong> - Moves horizontally or vertically</li>
                  <li><strong className="text-white">Bishop &#9815;</strong> - Moves diagonally</li>
                  <li><strong className="text-white">Knight &#9816;</strong> - Moves in an "L" shape (2+1 squares), can jump over pieces</li>
                  <li><strong className="text-white">Pawn &#9817;</strong> - Moves forward one square (two on first move), captures diagonally</li>
                </ul>
              </section>

              <section>
                <h3 className="text-base font-semibold text-white mb-2">Special Moves</h3>
                <ul className="space-y-2">
                  <li><strong className="text-white">Castling</strong> - King and Rook swap sides (if neither has moved and path is clear)</li>
                  <li><strong className="text-white">En Passant</strong> - Pawn can capture enemy pawn that just moved two squares</li>
                  <li><strong className="text-white">Promotion</strong> - Pawn reaching the opposite end becomes a Queen, Rook, Bishop, or Knight</li>
                </ul>
              </section>

              <section>
                <h3 className="text-base font-semibold text-white mb-2">Game End</h3>
                <ul className="space-y-2">
                  <li><strong className="text-white">Checkmate</strong> - King is in check with no escape. Game over!</li>
                  <li><strong className="text-white">Stalemate</strong> - No legal moves but King is not in check. Draw!</li>
                </ul>
              </section>

              <section>
                <h3 className="text-base font-semibold text-white mb-2">Controls</h3>
                <p>Click on a piece to select it, then click on a highlighted square to move. You can also drag and drop pieces.</p>
              </section>
            </div>
          </ScrollArea>
        </div>
      </div>
    </div>
  );
}
