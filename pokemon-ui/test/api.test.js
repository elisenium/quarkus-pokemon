import { describe, it, expect, beforeEach, vi } from 'vitest';
import * as api from '../src/api.js';

const sampleFighters = [{ name: 'Pikachu', type: 'Electric' }, { name: 'Bulbasaur', type: 'Grass' }];

describe('api', () => {
  beforeEach(() => {
    global.fetch = vi.fn();
  });

  it('getFighters should fetch and return json', async () => {
    global.fetch.mockResolvedValue({
      ok: true,
      json: async () => sampleFighters
    });
    const res = await api.getFighters();
    expect(res).toEqual(sampleFighters);
    expect(global.fetch).toHaveBeenCalled();
  });

  it('startFight should POST and return json', async () => {
    const resp = { winner: 'Pikachu' };
    global.fetch.mockResolvedValue({
      ok: true,
      json: async () => resp
    });
    // startFight expects an object with fighter1 and fighter2 (each having an id)
    const payload = {
      fighter1: { id: 1, name: 'Pikachu' },
      fighter2: { id: 2, name: 'Bulbasaur' }
    };
    const res = await api.startFight(payload);
    expect(res).toEqual(resp);
    expect(global.fetch).toHaveBeenCalled();
  });
});
