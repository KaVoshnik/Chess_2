import { PieceColor, PieceType, getPieceSymbol } from '@/lib/chessEngine';
import { Button } from '@/components/ui/button';

interface PromotionModalProps {
  color: PieceColor;
  onSelect: (pieceType: PieceType) => void;
  onCancel: () => void;
}

const promotionPieces: PieceType[] = ['queen', 'rook', 'bishop', 'knight'];

export function PromotionModal({ color, onSelect, onCancel }: PromotionModalProps) {
  return (
    <div 
      className="fixed inset-0 z-50 flex items-center justify-center bg-black/70 backdrop-blur-sm"
      data-testid="promotion-modal"
    >
      <div className="bg-[#0a0a1a] border border-white/10 rounded-xl p-6 shadow-2xl">
        <h3 className="text-xl font-bold text-center mb-4 text-white/90">
          Choose Promotion
        </h3>
        <div className="flex gap-3">
          {promotionPieces.map((type) => (
            <Button
              key={type}
              variant="ghost"
              className="w-16 h-16 text-4xl hover:bg-white/10 border border-white/10 rounded-lg"
              onClick={() => onSelect(type)}
              data-testid={`promote-${type}`}
            >
              {getPieceSymbol({ type, color })}
            </Button>
          ))}
        </div>
        <Button
          variant="ghost"
          className="w-full mt-4 text-white/60 hover:text-white"
          onClick={onCancel}
          data-testid="cancel-promotion"
        >
          Cancel
        </Button>
      </div>
    </div>
  );
}
